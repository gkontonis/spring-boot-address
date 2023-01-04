package at.bmlv.test.demo.rest.exception;

public class NoStacktraceException extends Exception {
    public NoStacktraceException() {
    }

    public NoStacktraceException(String message) {
        this(message, null, false);
    }

    public NoStacktraceException(String message, Throwable cause) {
        this(message, cause, false);
    }

    public NoStacktraceException(String message, Throwable cause, boolean enableSuppression) {
        super(message, cause, enableSuppression, false);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
