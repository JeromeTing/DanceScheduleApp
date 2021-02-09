package model;

import model.Student;

import java.util.ArrayList;
import java.util.List;

public class DanceClass {
    private int maxStudents = 5;

    private String className;                   // name of dance class
    private int time;                           // time of class as 2400
    private String teacherName;                 // name of the teacher of the class
    private String difficultyLevel;             // Difficult of the class as either "Open", "Intro", "Intermediate",
                                                // or "Advanced"
    private List<Student> registeredStudents;   // list of students registered in a class

    /* EFFECTS: constructs a dance class, where the class name, time and ay, teacher name and difficulty
     *          level are specified
     */
    public DanceClass(String className, int time, String teacherName, String difficultyLevel) {
        this.className = className;
        this.time = time;
        this.teacherName = teacherName;
        this.difficultyLevel = difficultyLevel;
        registeredStudents = new ArrayList<>();
    }

    // getters;
    public String getClassName() {
        return className;
    }

    public int getTime() {
        return time;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    // setters
    public void setClassName(String className) {
        this.className = className;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    /* REQUIRES: Student cannot be registered in the class
     * MODIFIES: this
     * EFFECTS: registers the student for the class if there is less than maxParticipants
     * and produces true if successful. Otherwise produce false.
     */
    public boolean registerStudent(Student student) {
        if (registeredStudents.size() < maxStudents && !registeredStudents.contains(student)) {
            registeredStudents.add(student);
            return true;
        }
        return false;
    }

    // EFFECT: returns the number of registered students
    public int size() {
        return registeredStudents.size();
    }

    // REQUIRES: student must be registered in the class
    // EFFECT: returns true if the student is registered, false otherwise
    public boolean contains(Student student) {
        return registeredStudents.contains(student);
    }

}
