package exception;

public class DatabaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DatabaseException(String message, Throwable cause) {//cause me sql expection aata j
        super(message, cause);
    }
}
