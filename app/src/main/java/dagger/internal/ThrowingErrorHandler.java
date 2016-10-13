package dagger.internal;

import dagger.internal.Linker.ErrorHandler;
import java.util.List;

public final class ThrowingErrorHandler implements ErrorHandler {
    public void handleErrors(List<String> errors) {
        if (!errors.isEmpty()) {
            StringBuilder message = new StringBuilder();
            message.append("Errors creating object graph:");
            for (String error : errors) {
                message.append("\n  ").append(error);
            }
            throw new IllegalStateException(message.toString());
        }
    }
}
