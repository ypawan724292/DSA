package lld.solutions.onlinestockbrokeragesystem;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
