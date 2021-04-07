package model;

import model.exceptions.StringLengthException;
import model.exceptions.StudentAlreadyRegisteredException;
import model.exceptions.TimeOutOfBoundsException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// represents a dance class that has a class name, a time, a teacher's name, and difficulty level
public class DanceClass implements Writable {
    public static final int MAX_STUDENTS = 15;  // Maximum number of students allowed in a class

    private String className;                   // name of dance class
    private int time;                           // time of class as 2400
    private String teacherName;                 // name of the teacher of the class
    private String difficultyLevel;             // Difficult of the class as either "Open", "Intro", "Intermediate",
                                                // or "Advanced"
    private List<Student> registeredStudents;   // list of students registered in a class

    /* EFFECTS: constructs a dance class, where the class name, time, teacher name, and difficulty
     *          level are given
     *          if stringLength is 0, then throws a StringLengthException
     *          if time is not between [0,2400]. throws a TimeOutOfBoundsExceptions
     */
    public DanceClass(String className, int time, String teacherName, String difficultyLevel)
            throws StringLengthException, TimeOutOfBoundsException {
        if (className.length() == 0 || teacherName.length() == 0 || difficultyLevel.length() == 0) {
            throw new StringLengthException("String is empty!");
        } else {
            this.className = className;
            this.teacherName = teacherName;
            this.difficultyLevel = difficultyLevel;
        }
        if (0 <= time && time <= 2400) {
            this.time = time;
        } else {
            throw new TimeOutOfBoundsException("Time is not between 0 and 2400, inclusively");
        }
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

    public List<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    // setters
    public void setClassName(String className) {
        this.className = className;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    // EFFECT: returns the number of registered students
    public int sizeOfClass() {
        return registeredStudents.size();
    }

    // EFFECT: returns true if the student is registered, false otherwise
    public boolean isStudentRegistered(Student student) {
        return registeredStudents.contains(student);
    }

    /* MODIFIES: this
     * EFFECTS: registers the student for the class if there is less than maxParticipants
     * and produces true if successful. Otherwise produce false.
     * If student is already registered in the class, throw StudentAlreadyRegisteredException
     */
    public boolean registerStudent(Student student) throws StudentAlreadyRegisteredException {
        if (isStudentRegistered(student)) {
            throw new StudentAlreadyRegisteredException("Student is already registered in the class");
        } else if (registeredStudents.size() < MAX_STUDENTS) {
            registeredStudents.add(student);
            return true;
        }
        return false;
    }

    /* REQUIRES: Student is registered in the class
     * MODIFIES: this
     * EFFECTS: removes the student from the dance class
     */
    public void removeStudent(Student student) {
        registeredStudents.remove(student);
    }

    @Override
    // EFFECTS: Creates a json object and puts the dance class details (className, time, teacherName, difficultyLevel,
    // as well as the registered students to this object and returns it
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("className", className);
        json.put("time", time);
        json.put("teacherName", teacherName);
        json.put("difficultLevel", difficultyLevel);
        json.put("registeredStudents", studentsToJson());
        return json;
    }

    //EFFECTS: returns students in this danceClass to a JSON array
    private JSONArray studentsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Student s: registeredStudents) {
            jsonArray.put(s.toJson());
        }
        return jsonArray;
    }
}
