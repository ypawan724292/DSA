package lld.solutions.onlinestockbrokeragesystem;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String message) {
        super(message);
    }
}
