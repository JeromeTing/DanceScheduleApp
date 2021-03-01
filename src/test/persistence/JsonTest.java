package persistence;

import model.DanceClass;
import model.Day;
import model.Student;
import model.WeeklySchedule;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkEmptySchedule(WeeklySchedule ws) {

        for (Day d : ws.getWeeklySchedule()) {
            assertEquals(0, d.getDaySchedule().size());
        }
        assertEquals("Monday", ws.getWeeklySchedule().get(0).getDayName());
        assertEquals("Tuesday", ws.getWeeklySchedule().get(1).getDayName());
        assertEquals("Wednesday", ws.getWeeklySchedule().get(2).getDayName());
        assertEquals("Thursday", ws.getWeeklySchedule().get(3).getDayName());
        assertEquals("Friday", ws.getWeeklySchedule().get(4).getDayName());
        assertEquals("Saturday", ws.getWeeklySchedule().get(5).getDayName());
        assertEquals("Sunday", ws.getWeeklySchedule().get(6).getDayName());
    }

    protected void checkDanceClass1(WeeklySchedule ws) {
        DanceClass danceClass1 = ws.getWeeklySchedule().get(0).getDanceClass(0);
        assertEquals(0, danceClass1.getRegisteredStudents().size());
        assertEquals("Erik", danceClass1.getTeacherName());
        assertEquals("Hip Hop", danceClass1.getClassName());
        assertEquals(1200, danceClass1.getTime());
        assertEquals("Open", danceClass1.getDifficultyLevel());
    }

    protected void checkDanceClass2(WeeklySchedule ws) {
        DanceClass danceClass2 = ws.getWeeklySchedule().get(0).getDanceClass(1);
        assertEquals(1, danceClass2.getRegisteredStudents().size());
        assertEquals("Amie", danceClass2.getTeacherName());
        assertEquals("Waacking", danceClass2.getClassName());
        assertEquals(1400, danceClass2.getTime());
        assertEquals("Intermediate", danceClass2.getDifficultyLevel());

        Student student = danceClass2.getRegisteredStudents().get(0);
        assertEquals("Jerome", student.getName());
        assertEquals(100, student.getMembershipNumber());
    }

    protected void checkDanceClass3(WeeklySchedule ws) {
        DanceClass danceClass3 = ws.getWeeklySchedule().get(4).getDanceClass(0);
        assertEquals(1, danceClass3.getRegisteredStudents().size());
        assertEquals("Squidjit", danceClass3.getTeacherName());
        assertEquals("Popping", danceClass3.getClassName());
        assertEquals(1600, danceClass3.getTime());
        assertEquals("Advanced", danceClass3.getDifficultyLevel());

        Student student = danceClass3.getRegisteredStudents().get(0);
        assertEquals("Jon", student.getName());
        assertEquals(0, student.getMembershipNumber());
    }

    protected void checkWeeklyScheduleSomeClassesAndStudents(WeeklySchedule ws) {
        assertEquals(2,ws.getWeeklySchedule().get(0).getDaySchedule().size());
        assertEquals(0,ws.getWeeklySchedule().get(1).getDaySchedule().size());
        assertEquals(0,ws.getWeeklySchedule().get(2).getDaySchedule().size());
        assertEquals(0,ws.getWeeklySchedule().get(3).getDaySchedule().size());
        assertEquals(1,ws.getWeeklySchedule().get(4).getDaySchedule().size());
        assertEquals(0,ws.getWeeklySchedule().get(5).getDaySchedule().size());
        assertEquals(0,ws.getWeeklySchedule().get(6).getDaySchedule().size());

        checkDanceClass1(ws);
        checkDanceClass2(ws);
        checkDanceClass3(ws);
    }
}

