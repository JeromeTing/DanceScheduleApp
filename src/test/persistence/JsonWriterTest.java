package persistence;

import model.DanceClass;
import model.Day;
import model.Student;
import model.WeeklySchedule;
import model.exceptions.StringLengthException;
import model.exceptions.StudentAlreadyRegisteredException;
import model.exceptions.TimeOutOfBoundsException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// Code inspired and based on JsonSerializationDemo
//

public class JsonWriterTest extends JsonTest{

    @Test
    public void testWriterInvalidName() {
        try {
            WeeklySchedule ws = new WeeklySchedule();
            JsonWriter writer = new JsonWriter("./data/imp\0ssible:\t_Fi!leName.json");
            writer.open();
            fail("IOException expected");
        } catch (IOException e) {
            // should pass
        }
    }

    @Test
    public void testWriterEmptyWeeklySchedule() throws StringLengthException, TimeOutOfBoundsException,
            StudentAlreadyRegisteredException {
        try {
            WeeklySchedule ws = new WeeklySchedule();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWeeklySchedule.json");
            writer.open();
            writer.write(ws);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWeeklySchedule.json");
            ws = reader.read();
            checkEmptySchedule(ws);
        } catch (IOException e) {
            fail("Exception should not be thrown here");
        }
    }

    public void addToEmptyScheduleForTest(WeeklySchedule ws) throws StringLengthException, TimeOutOfBoundsException,
            StudentAlreadyRegisteredException {
        Day monday = ws.getWeeklySchedule().get(0);
        Day friday = ws.getWeeklySchedule().get(4);
        DanceClass danceClass1 =
                new DanceClass("Hip Hop", 1200, "Erik", "Open");
        DanceClass danceClass2 =
                new DanceClass("Waacking", 1400, "Amie", "Intermediate");
        DanceClass danceClass3 =
                new DanceClass("Popping", 1600, "Squidjit", "Advanced");
        monday.addDanceClass(danceClass1);
        monday.addDanceClass(danceClass2);
        friday.addDanceClass(danceClass3);

        Student student1 = new Student("Jerome", 100);
        Student student2 = new Student("Jon", 0);

        danceClass2.registerStudent(student1);
        danceClass3.registerStudent(student2);
    }

    @Test
    void testWriterWeeklyScheduleSOmeClassesAndStudents() {
        try {
            WeeklySchedule ws = new WeeklySchedule();
            addToEmptyScheduleForTest(ws);
            JsonWriter writer = new JsonWriter("./data/testWriterWeeklyScheduleSOmeClassesAndStudents.json");
            writer.open();
            writer.write(ws);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterWeeklyScheduleSOmeClassesAndStudents.json");
            ws = reader.read();
            checkWeeklyScheduleSomeClassesAndStudents(ws);
        } catch (IOException | StringLengthException | TimeOutOfBoundsException | StudentAlreadyRegisteredException e) {
            fail("Exception should not be thrown here");
        }
    }
}

