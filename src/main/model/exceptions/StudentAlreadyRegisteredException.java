package model.exceptions;

public class StudentAlreadyRegisteredException extends Exception {
    public StudentAlreadyRegisteredException(String msg) {
        super(msg);
    }
}
