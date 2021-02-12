package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleTest {

    private DanceClass danceClass1;
    private DanceClass danceClass2;
    private DanceClass danceClass3;
    private DanceClass danceClass4;
    private DanceClass danceClass5;
    private Student jerome;
    private Day tuesdaySchedule;

    @BeforeEach
    public void runBefore() {
        danceClass1 = new DanceClass("Hip Hop", 1930, "Erik", "Advanced");
        danceClass2 = new DanceClass("Krump", 1230, "Jane", "Open");
        danceClass3 = new DanceClass("Popping", 2030, "Eric", "Intermediate");
        danceClass4 = new DanceClass("Tutting", 1000, "Shazam", "Intro");
        danceClass5 = new DanceClass("Waacking", 1600, "Amie", "Advanced");

        jerome = new Student("Jerome", 106);

        tuesdaySchedule = new Day("Tuesday");
    }

    @Test
    public void testWeeklyScheduleConstructorDefault() {
        WeeklySchedule weeklySchedule = new WeeklySchedule();
        List<Day> week = weeklySchedule.getWeeklySchedule();

        assertEquals(7, week.size());
        assertEquals("Monday",  week.get(0).getDayName());
        assertEquals("Tuesday",  week.get(1).getDayName());
        assertEquals("Wednesday",  week.get(2).getDayName());
        assertEquals("Thursday",  week.get(3).getDayName());
        assertEquals("Friday",  week.get(4).getDayName());
        assertEquals("Saturday",  week.get(5).getDayName());
        assertEquals("Sunday",  week.get(6).getDayName());
    }

    @Test
    public void testStudent() {
        assertEquals("Jerome", jerome.getName());
        assertEquals(106, jerome.getMembershipNumber());
    }
    @Test
    public void testDanceClass() {
        assertEquals("Hip Hop", danceClass1.getClassName());
        assertEquals(1930, danceClass1.getTime());
        assertEquals("Erik", danceClass1.getTeacherName());
        assertEquals("Advanced", danceClass1.getDifficultyLevel());
    }

    @Test
    public void testChangeDanceClass() {
        danceClass1.setTeacherName("Jane");
        danceClass1.setClassName("Krump");
        danceClass1.setDifficultyLevel("Open");
        danceClass1.setTime(1900);

        assertEquals("Krump", danceClass1.getClassName());
        assertEquals(1900, danceClass1.getTime());
        assertEquals("Jane", danceClass1.getTeacherName());
        assertEquals("Open", danceClass1.getDifficultyLevel());
    }
    @Test
    public void testRegisterStudentEmptyClass() {
        assertTrue(danceClass1.registerStudent(jerome));

        assertEquals(1,danceClass1.size());
        assertTrue(danceClass1.contains(jerome));
    }

    @Test
    public void testRegisterStudentAlreadyInClass() {
        assertTrue(danceClass1.registerStudent(jerome));
        assertFalse(danceClass1.registerStudent(jerome));

        assertEquals(1,danceClass1.size());
        assertTrue(danceClass1.contains(jerome));
    }
    @Test
    public void testRegisterStudentMultipleStudents() {
        Student jon = new Student("Jon", 100);
        Student bella = new Student("Bella", 101);
        Student josh = new Student("Josh", 102);
        Student cora = new Student ("Cora", 103);
        Student trent = new Student ("Trent", 104);

        assertTrue(danceClass1.registerStudent(jon));
        assertTrue(danceClass1.registerStudent(bella));
        assertTrue(danceClass1.registerStudent(josh));
        assertTrue(danceClass1.registerStudent(cora));
        assertTrue(danceClass1.registerStudent(trent));

        assertEquals(5, danceClass1.size());

        assertFalse(danceClass1.registerStudent(jerome));

        assertEquals(5, danceClass1.size());
    }
    @Test
    public void testAddDanceClassEmptyDaySchedule() {
        tuesdaySchedule.addDanceClass(danceClass1);

        assertEquals(1, tuesdaySchedule.getDaySchedule().size());
        assertEquals(danceClass1, tuesdaySchedule.getDanceClass(0));
    }
    @Test
    public void testAddDanceClassOneClassInDaySchedule() {
        tuesdaySchedule.addDanceClass(danceClass1);
        tuesdaySchedule.addDanceClass(danceClass2);

        assertEquals(danceClass2, tuesdaySchedule.getDanceClass(0));
        assertEquals(danceClass1, tuesdaySchedule.getDanceClass(1));

        assertEquals(2, tuesdaySchedule.getDaySchedule().size());
    }

    @Test
    public void testAddDanceClassMultipleClass() {
        tuesdaySchedule.addDanceClass(danceClass1);
        tuesdaySchedule.addDanceClass(danceClass2);
        tuesdaySchedule.addDanceClass(danceClass3);
        tuesdaySchedule.addDanceClass(danceClass4);
        tuesdaySchedule.addDanceClass(danceClass5);

        assertEquals(5, tuesdaySchedule.getDaySchedule().size());

        assertEquals(danceClass4, tuesdaySchedule.getDanceClass(0));
        assertEquals(danceClass2, tuesdaySchedule.getDanceClass(1));
        assertEquals(danceClass5, tuesdaySchedule.getDanceClass(2));
        assertEquals(danceClass1, tuesdaySchedule.getDanceClass(3));
        assertEquals(danceClass3, tuesdaySchedule.getDanceClass(4));

    }
    @Test
    public void removeDanceClassOneClass() {
        tuesdaySchedule.addDanceClass(danceClass1);
        tuesdaySchedule.addDanceClass(danceClass2);
        tuesdaySchedule.addDanceClass(danceClass3);
        tuesdaySchedule.addDanceClass(danceClass4);

        tuesdaySchedule.removeDanceClass(danceClass1);

        assertEquals(danceClass4, tuesdaySchedule.getDanceClass(0));
        assertEquals(danceClass2, tuesdaySchedule.getDanceClass(1));
        assertEquals(danceClass3, tuesdaySchedule.getDanceClass(2));

        assertEquals(3, tuesdaySchedule.getDaySchedule().size());
    }
    @Test
    public void removeDanceClassMultiple() {
        tuesdaySchedule.addDanceClass(danceClass1);
        tuesdaySchedule.addDanceClass(danceClass2);
        tuesdaySchedule.addDanceClass(danceClass3);
        tuesdaySchedule.addDanceClass(danceClass4);

        tuesdaySchedule.removeDanceClass(danceClass1);
        tuesdaySchedule.removeDanceClass(danceClass4);

        assertEquals(danceClass2, tuesdaySchedule.getDanceClass(0));
        assertEquals(danceClass3, tuesdaySchedule.getDanceClass(1));

        assertEquals(2, tuesdaySchedule.getDaySchedule().size());
    }
}