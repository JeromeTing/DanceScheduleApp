package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a student which has a name, and a membership number
public class Student implements Writable {
    private String name;            // name of the student
    private int membershipNumber;   // membership number of the student, if not a member input 0

    // REQUIRES: studentName must have a string length greater than 0, membership number must be > 0
    // EFFECTS: constructs a student with their name and their membership number
    public Student(String studentName, int membershipNum) {
        name = studentName;
        membershipNumber = membershipNum;
    }

    // getters
    public String getName() {
        return name;
    }

    public int getMembershipNumber() {
        return membershipNumber;
    }

    @Override

    // EFFECTS: creates a json object and puts the name, and membershipNumber of the student to the object and
    // returns it
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("membershipNumber", membershipNumber);
        return json;
    }
}
