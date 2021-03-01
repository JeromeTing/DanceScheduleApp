package persistence;

import model.DanceClass;
import model.Day;
import model.Student;
import model.WeeklySchedule;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    public void testReaderNoFileExists() {
        JsonReader reader = new JsonReader("./data/noFileExists.json");
        try {
            WeeklySchedule ws = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyWeeklySchedule() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWeeklySchedule.json");
        try {
            WeeklySchedule ws = reader.read();
            checkEmptySchedule(ws);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderWeeklyScheduleSomeClassesAndStudents() {
        JsonReader reader = new JsonReader(("./data/testReaderWeeklyScheduleSomeClassesAndStudents.json"));
        try {
            WeeklySchedule ws = reader.read();
            checkWeeklyScheduleSomeClassesAndStudents(ws);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
