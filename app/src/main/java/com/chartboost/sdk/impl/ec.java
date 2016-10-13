package com.chartboost.sdk.impl;

final class ec {
    static final ec a = new ec();
    long b;
    private eb c;

    private ec() {
    }

    eb a() {
        synchronized (this) {
            if (this.c != null) {
                eb ebVar = this.c;
                this.c = ebVar.d;
                ebVar.d = null;
                this.b -= 2048;
                return ebVar;
            }
            return new eb();
        }
    }

    void a(eb ebVar) {
        if (ebVar.d == null && ebVar.e == null) {
            synchronized (this) {
                if (this.b + 2048 > 65536) {
                    return;
                }
                this.b += 2048;
                ebVar.d = this.c;
                ebVar.c = 0;
                ebVar.b = 0;
                this.c = ebVar;
                return;
            }
        }
        throw new IllegalArgumentException();
    }
}
