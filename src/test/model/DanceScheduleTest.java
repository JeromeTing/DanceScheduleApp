package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DanceScheduleTest {

    private DanceClass danceClass1;
    private DanceClass danceClass2;
    private DanceClass danceClass3;
    private DanceClass danceClass4;
    private DanceClass danceClass5;
    private DanceClass danceClass6;

    private Student jerome;
    private Student jon;
    private Student bella;
    private Student josh;
    private Student cora;
    private Student trent;
    private Student erik;
    private Student jeav;
    private Student emma;
    private Student raymund;
    private Student raegan;
    private Student helen;
    private Student marvin;
    private Student kayla;
    private Student tiernan;
    private Student petra;

    private Day tuesdaySchedule;

    @BeforeEach
    public void runBefore() {
        danceClass1 = new DanceClass("Hip Hop", 1930, "Erik", "Advanced");
        danceClass2 = new DanceClass("Krump", 1230, "Jane", "Open");
        danceClass3 = new DanceClass("Popping", 2030, "Eric", "Intermediate");
        danceClass4 = new DanceClass("Tutting", 1000, "Shazam", "Intro");
        danceClass5 = new DanceClass("Waacking", 1600, "Amie", "Advanced");
        danceClass6 = new DanceClass("Kpop", 1430, "Marvin", "Open");

        jerome = new Student("Jerome", 99);

        tuesdaySchedule = new Day("Tuesday");
    }

    @BeforeEach
    public void runBeforeStudents() {
        jon = new Student("Jon", 100);
        bella = new Student("Bella", 101);
        josh = new Student("Josh", 102);
        cora = new Student ("Cora", 103);
        trent = new Student ("Trent", 105);
        erik = new Student ("Erik", 106);
        jeav = new Student ("Jeav", 107);
        emma = new Student ("Emma", 108);
        raymund = new Student ("Raymund", 109);
        raegan = new Student ("Raegan", 110);
        helen = new Student ("Helen", 111);
        marvin = new Student ("Marvin", 112);
        kayla = new Student ("Kayla", 113);
        tiernan = new Student ("Tiernan", 114);
        petra = new Student ("Petra", 115);
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
        assertEquals(99, jerome.getMembershipNumber());
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

        assertEquals(1,danceClass1.sizeOfClass());
        assertTrue(danceClass1.isStudentRegistered(jerome));
    }

    @Test
    public void testRegisterStudentAlreadyInClass() {
        assertTrue(danceClass1.registerStudent(jerome));
        assertFalse(danceClass1.registerStudent(jerome));

        assertEquals(1,danceClass1.sizeOfClass());
        assertTrue(danceClass1.isStudentRegistered(jerome));
    }

    @Test
    public void testRemoveStudentOneStudent() {
        assertTrue(danceClass1.registerStudent(jon));
        danceClass1.removeStudent(jon);

        assertEquals(0, danceClass1.getRegisteredStudents().size());
    }

    @Test
    public void testRemoveMultipleStudents() {
        assertTrue(danceClass1.registerStudent(jon));
        assertTrue(danceClass1.registerStudent(bella));
        assertTrue(danceClass1.registerStudent(josh));

        danceClass1.removeStudent(jon);
        danceClass1.removeStudent(josh);

        assertEquals(1, danceClass1.sizeOfClass());
        assertEquals(bella, danceClass1.getRegisteredStudents().get(0));
    }

    @Test
    public void testRegisterStudentMultipleStudentsMax() {
        assertTrue(danceClass1.registerStudent(jon));
        assertTrue(danceClass1.registerStudent(bella));
        assertTrue(danceClass1.registerStudent(josh));
        assertTrue(danceClass1.registerStudent(cora));
        assertTrue(danceClass1.registerStudent(trent));
        assertTrue(danceClass1.registerStudent(erik));
        assertTrue(danceClass1.registerStudent(jeav));
        assertTrue(danceClass1.registerStudent(emma));
        assertTrue(danceClass1.registerStudent(raegan));
        assertTrue(danceClass1.registerStudent(raymund));
        assertTrue(danceClass1.registerStudent(helen));
        assertTrue(danceClass1.registerStudent(marvin));
        assertTrue(danceClass1.registerStudent(kayla));
        assertTrue(danceClass1.registerStudent(tiernan));
        assertTrue(danceClass1.registerStudent(petra));

        assertEquals(15, danceClass1.sizeOfClass());

        assertFalse(danceClass1.registerStudent(jerome));

        assertEquals(15, danceClass1.sizeOfClass());
    }
    @Test
    public void testAddDanceClassEmptyDaySchedule() {
        tuesdaySchedule.addDanceClass(danceClass1);

        assertEquals(1, tuesdaySchedule.getDaySchedule().size());
        assertEquals(danceClass1, tuesdaySchedule.getDanceClass(0));
    }
    @Test
    public void testAddDanceClassOneClassInDaySchedule() {
        assertTrue(tuesdaySchedule.addDanceClass(danceClass1));
        assertTrue(tuesdaySchedule.addDanceClass(danceClass2));

        assertEquals(danceClass2, tuesdaySchedule.getDanceClass(0));
        assertEquals(danceClass1, tuesdaySchedule.getDanceClass(1));

        assertEquals(2, tuesdaySchedule.getDaySchedule().size());
    }

    @Test
    public void testAddDanceClassMultipleClass() {
        assertTrue(tuesdaySchedule.addDanceClass(danceClass1));
        assertTrue(tuesdaySchedule.addDanceClass(danceClass2));
        assertTrue(tuesdaySchedule.addDanceClass(danceClass3));
        assertTrue(tuesdaySchedule.addDanceClass(danceClass4));
        assertTrue(tuesdaySchedule.addDanceClass(danceClass5));
        assertTrue(tuesdaySchedule.addDanceClass(danceClass6));

        assertEquals(6, tuesdaySchedule.getDaySchedule().size());

        assertEquals(danceClass4, tuesdaySchedule.getDanceClass(0));
        assertEquals(danceClass2, tuesdaySchedule.getDanceClass(1));
        assertEquals(danceClass6, tuesdaySchedule.getDanceClass(2));
        assertEquals(danceClass5, tuesdaySchedule.getDanceClass(3));
        assertEquals(danceClass1, tuesdaySchedule.getDanceClass(4));
        assertEquals(danceClass3, tuesdaySchedule.getDanceClass(5));

    }

    @Test
    public void testAddDanceClassSameTime() {
        DanceClass danceClass7 = new DanceClass("Hip Hop", 1930, "Kayla","Open");
        assertTrue(tuesdaySchedule.addDanceClass(danceClass1));

        assertEquals(1, tuesdaySchedule.getDaySchedule().size());

        assertFalse(tuesdaySchedule.addDanceClass(danceClass7));

        assertEquals(1, tuesdaySchedule.getDaySchedule().size());
        assertEquals(danceClass1,tuesdaySchedule.getDanceClass(0));
    }
    @Test
    public void removeDanceClassOneClass() {
        assertTrue(tuesdaySchedule.addDanceClass(danceClass1));
        assertTrue(tuesdaySchedule.addDanceClass(danceClass2));
        assertTrue(tuesdaySchedule.addDanceClass(danceClass3));
        assertTrue(tuesdaySchedule.addDanceClass(danceClass4));

        tuesdaySchedule.removeDanceClass(danceClass1);

        assertEquals(danceClass4, tuesdaySchedule.getDanceClass(0));
        assertEquals(danceClass2, tuesdaySchedule.getDanceClass(1));
        assertEquals(danceClass3, tuesdaySchedule.getDanceClass(2));

        assertEquals(3, tuesdaySchedule.getDaySchedule().size());
    }
    @Test
    public void removeDanceClassMultiple() {
        assertTrue(tuesdaySchedule.addDanceClass(danceClass1));
        assertTrue(tuesdaySchedule.addDanceClass(danceClass2));
        assertTrue(tuesdaySchedule.addDanceClass(danceClass3));
        assertTrue(tuesdaySchedule.addDanceClass(danceClass4));

        tuesdaySchedule.removeDanceClass(danceClass1);
        tuesdaySchedule.removeDanceClass(danceClass4);

        assertEquals(danceClass2, tuesdaySchedule.getDanceClass(0));
        assertEquals(danceClass3, tuesdaySchedule.getDanceClass(1));

        assertEquals(2, tuesdaySchedule.getDaySchedule().size());
    }
}