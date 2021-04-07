package model.exceptions;

// Represents a that the integer is not within 0 to 2400 exception
public class TimeOutOfBoundsException extends Exception {
    public TimeOutOfBoundsException(String msg) {
        super(msg);
    }
}
