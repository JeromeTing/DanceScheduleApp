package model.exceptions;

// Represents an exception for a student being already registered in a dance class
public class StudentAlreadyRegisteredException extends Exception {
    public StudentAlreadyRegisteredException(String msg) {
        super(msg);
    }
}
