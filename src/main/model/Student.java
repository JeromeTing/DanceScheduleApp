package model;

public class Student {
    private String name;            // name of the student
    private int membershipNumber;   // membership number of the student, if not a member input 0

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
}
