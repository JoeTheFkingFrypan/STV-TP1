package ca.uqac.core;

public class FileLoaderException extends RuntimeException {

    public FileLoaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileLoaderException(Throwable cause) {
        super(cause);
    }
}
