package model.exceptions;

import java.sql.Time;

public class TimeOutOfBoundsException extends Exception {
    public TimeOutOfBoundsException(String msg) {
        super(msg);
    }
}
