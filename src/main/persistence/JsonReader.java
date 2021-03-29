package persistence;

import model.DanceClass;
import model.Day;
import model.Student;
import model.WeeklySchedule;
import model.exceptions.StringLengthException;
import model.exceptions.StudentAlreadyRegisteredException;
import model.exceptions.TimeOutOfBoundsException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads the weekly dance schedule from JSON data stored in file
// Code inspired and based on JsonSerializationDemo
// (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder builder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> builder.append(s));
        }
        return builder.toString();
    }

    // EFFECTS: reads source file and returns it
    public WeeklySchedule read() throws IOException, StringLengthException,
            TimeOutOfBoundsException, StudentAlreadyRegisteredException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWeeklySchedule(jsonObject);
    }

    // EFFECTS: parses WeeklySchedule from JSON Object and returns it
    private WeeklySchedule parseWeeklySchedule(JSONObject jsonObject) throws StringLengthException,
            TimeOutOfBoundsException, StudentAlreadyRegisteredException {
        WeeklySchedule ws = new WeeklySchedule();
        for (Day d: ws.getWeeklySchedule()) {
            addDays(d, jsonObject);
        }

        return ws;
    }

    // MODIFIES: ws
    // EFFECTS: parses days and adds them to respective week
    private void addDays(Day day, JSONObject jsonObject) throws StringLengthException, TimeOutOfBoundsException,
            StudentAlreadyRegisteredException {
        JSONArray jsonArray = jsonObject.getJSONArray("days");
        for (Object json: jsonArray) {
            JSONObject jsonDay = (JSONObject) json;
            addDanceClasses(day, jsonDay);
        }
    }

    // MODIFIES: ws
    // EFFECTS: parses dance classes from JSON object and adds them to respective day, also adds students if
    // registered student list is not empty
    private void addDanceClasses(Day day, JSONObject jsonObject) throws StringLengthException,
            TimeOutOfBoundsException, StudentAlreadyRegisteredException {
        JSONArray jsonArray = jsonObject.getJSONArray(("daySchedule"));
        String dayMatch = jsonObject.getString("day");
        if (!jsonArray.isEmpty() && day.getDayName().equals(dayMatch)) {
            for (Object json : jsonArray) {
                JSONObject classDetails = (JSONObject) json;

                String teacherName = classDetails.getString("teacherName");
                String className = classDetails.getString("className");
                int time = classDetails.getInt("time");
                String difficultyLevel = classDetails.getString("difficultLevel");

                DanceClass danceClass = new DanceClass(className, time, teacherName, difficultyLevel);
                day.addDanceClass(danceClass);

                JSONArray jsonArrayStudents = classDetails.getJSONArray(("registeredStudents"));
                if (!jsonArrayStudents.isEmpty()) {
                    addStudents(jsonArrayStudents, danceClass);
                }
            }
        }
    }

    // MODIFIES: ws
    // EFFECTS: parses students from JSON object and adds them to dance class
    private void addStudents(JSONArray jsonArray, DanceClass danceClass) throws StudentAlreadyRegisteredException {
        for (Object json: jsonArray) {
            JSONObject studentDetails = (JSONObject) json;

            String studentName = studentDetails.getString("name");
            int membershipNum = studentDetails.getInt("membershipNumber");
            Student student = new Student(studentName, membershipNum);

            danceClass.registerStudent(student);
        }
    }





}
