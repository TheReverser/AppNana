package dagger.internal;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class ArrayQueue<E> extends AbstractCollection<E> implements Queue<E>, Cloneable, Serializable {
    private static final int MIN_INITIAL_CAPACITY = 8;
    private static final long serialVersionUID = 2340985798034038923L;
    private transient Object[] elements;
    private transient int head;
    private transient int tail;

    private class QueueIterator implements Iterator<E> {
        private int cursor;
        private int fence;
        private int lastRet;

        private QueueIterator() {
            this.cursor = ArrayQueue.this.head;
            this.fence = ArrayQueue.this.tail;
            this.lastRet = -1;
        }

        public boolean hasNext() {
            return this.cursor != this.fence;
        }

        public E next() {
            if (this.cursor == this.fence) {
                throw new NoSuchElementException();
            }
            E result = ArrayQueue.this.elements[this.cursor];
            if (ArrayQueue.this.tail != this.fence || result == null) {
                throw new ConcurrentModificationException();
            }
            this.lastRet = this.cursor;
            this.cursor = (this.cursor + 1) & (ArrayQueue.this.elements.length - 1);
            return result;
        }

        public void remove() {
            if (this.lastRet < 0) {
                throw new IllegalStateException();
            }
            if (ArrayQueue.this.delete(this.lastRet)) {
                this.cursor = (this.cursor - 1) & (ArrayQueue.this.elements.length - 1);
                this.fence = ArrayQueue.this.tail;
            }
            this.lastRet = -1;
        }
    }

    private void allocateElements(int numElements) {
        int initialCapacity = MIN_INITIAL_CAPACITY;
        if (numElements >= MIN_INITIAL_CAPACITY) {
            initialCapacity = numElements;
            initialCapacity |= initialCapacity >>> 1;
            initialCapacity |= initialCapacity >>> 2;
            initialCapacity |= initialCapacity >>> 4;
            initialCapacity |= initialCapacity >>> MIN_INITIAL_CAPACITY;
            initialCapacity = (initialCapacity | (initialCapacity >>> 16)) + 1;
            if (initialCapacity < 0) {
                initialCapacity >>>= 1;
            }
        }
        this.elements = new Object[initialCapacity];
    }

    private void doubleCapacity() {
        int p = this.head;
        int n = this.elements.length;
        int r = n - p;
        int newCapacity = n << 1;
        if (newCapacity < 0) {
            throw new IllegalStateException("Sorry, queue too big");
        }
        Object[] a = new Object[newCapacity];
        System.arraycopy(this.elements, p, a, 0, r);
        System.arraycopy(this.elements, 0, a, r, p);
        this.elements = a;
        this.head = 0;
        this.tail = n;
    }

    public ArrayQueue() {
        this.elements = new Object[16];
    }

    public ArrayQueue(int numElements) {
        allocateElements(numElements);
    }

    public ArrayQueue(Collection<? extends E> c) {
        allocateElements(c.size());
        addAll(c);
    }

    public boolean add(E e) {
        if (e == null) {
            throw new NullPointerException("e == null");
        }
        this.elements[this.tail] = e;
        int length = (this.tail + 1) & (this.elements.length - 1);
        this.tail = length;
        if (length == this.head) {
            doubleCapacity();
        }
        return true;
    }

    public boolean offer(E e) {
        return add(e);
    }

    public E remove() {
        E x = poll();
        if (x != null) {
            return x;
        }
        throw new NoSuchElementException();
    }

    public E poll() {
        int h = this.head;
        E result = this.elements[h];
        if (result == null) {
            return null;
        }
        this.elements[h] = null;
        this.head = (h + 1) & (this.elements.length - 1);
        return result;
    }

    public E element() {
        E result = this.elements[this.head];
        if (result != null) {
            return result;
        }
        throw new NoSuchElementException();
    }

    public E peek() {
        return this.elements[this.head];
    }

    private boolean delete(int i) {
        Object[] elements = this.elements;
        int mask = elements.length - 1;
        int h = this.head;
        int t = this.tail;
        int front = (i - h) & mask;
        int back = (t - i) & mask;
        if (front >= ((t - h) & mask)) {
            throw new ConcurrentModificationException();
        } else if (front < back) {
            if (h <= i) {
                System.arraycopy(elements, h, elements, h + 1, front);
            } else {
                System.arraycopy(elements, 0, elements, 1, i);
                elements[0] = elements[mask];
                System.arraycopy(elements, h, elements, h + 1, mask - h);
            }
            elements[h] = null;
            this.head = (h + 1) & mask;
            return false;
        } else {
            if (i < t) {
                System.arraycopy(elements, i + 1, elements, i, back);
                this.tail = t - 1;
            } else {
                System.arraycopy(elements, i + 1, elements, i, mask - i);
                elements[mask] = elements[0];
                System.arraycopy(elements, 1, elements, 0, t);
                this.tail = (t - 1) & mask;
            }
            return true;
        }
    }

    public int size() {
        return (this.tail - this.head) & (this.elements.length - 1);
    }

    public boolean isEmpty() {
        return this.head == this.tail;
    }

    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        int mask = this.elements.length - 1;
        int i = this.head;
        while (true) {
            Object x = this.elements[i];
            if (x == null) {
                return false;
            }
            if (o.equals(x)) {
                return true;
            }
            i = (i + 1) & mask;
        }
    }

    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }
        int mask = this.elements.length - 1;
        int i = this.head;
        while (true) {
            Object x = this.elements[i];
            if (x == null) {
                return false;
            }
            if (o.equals(x)) {
                delete(i);
                return true;
            }
            i = (i + 1) & mask;
        }
    }

    public void clear() {
        int h = this.head;
        int t = this.tail;
        if (h != t) {
            this.tail = 0;
            this.head = 0;
            int i = h;
            int mask = this.elements.length - 1;
            do {
                this.elements[i] = null;
                i = (i + 1) & mask;
            } while (i != t);
        }
    }

    public Object[] toArray() {
        return toArray(new Object[size()]);
    }

    public <T> T[] toArray(T[] a) {
        int size = size();
        if (a.length < size) {
            a = (Object[]) ((Object[]) Array.newInstance(a.getClass().getComponentType(), size));
        }
        if (this.head < this.tail) {
            System.arraycopy(this.elements, this.head, a, 0, size());
        } else if (this.head > this.tail) {
            int headPortionLen = this.elements.length - this.head;
            System.arraycopy(this.elements, this.head, a, 0, headPortionLen);
            System.arraycopy(this.elements, 0, a, headPortionLen, this.tail);
        }
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    public ArrayQueue<E> clone() {
        try {
            ArrayQueue<E> result = (ArrayQueue) super.clone();
            E[] newElements = (Object[]) Array.newInstance(this.elements.getClass().getComponentType(), this.elements.length);
            System.arraycopy(this.elements, 0, newElements, 0, this.elements.length);
            result.elements = newElements;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeInt(size());
        int mask = this.elements.length - 1;
        for (int i = this.head; i != this.tail; i = (i + 1) & mask) {
            s.writeObject(this.elements[i]);
        }
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        int size = s.readInt();
        allocateElements(size);
        this.head = 0;
        this.tail = size;
        for (int i = 0; i < size; i++) {
            this.elements[i] = s.readObject();
        }
    }
}
