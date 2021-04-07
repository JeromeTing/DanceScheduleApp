package model;

import model.exceptions.StringLengthException;
import model.exceptions.StudentAlreadyRegisteredException;
import model.exceptions.TimeOutOfBoundsException;
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
    public void runBefore() throws StringLengthException, TimeOutOfBoundsException {
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
    public void testDanceClassConstructorNoException() {
        try {
            DanceClass dc = new DanceClass("Hip Hop", 1500, "Jerome", "Open");
            assertEquals("Hip Hop", dc.getClassName());
            assertEquals("Jerome", dc.getTeacherName());
            assertEquals("Open", dc.getDifficultyLevel());
            assertEquals(1500, dc.getTime());
        } catch (StringLengthException e) {
            fail("StringLengthException not expected");
        } catch (TimeOutOfBoundsException e) {
            fail("TimeOutOfBoundException not expected");
        }
    }

    @Test
    public void testDanceClassConstructorClassNameLengthException() {
        try {
            DanceClass dc = new DanceClass("", 1500, "Jerome", "Open");
            fail("StringLengthException expected");
        } catch (StringLengthException e) {
            // pass
        } catch (TimeOutOfBoundsException e) {
            fail("TimeOutOfBoundException not expected");
        }
    }

    @Test
    public void testDanceClassConstructorTeacherNameLengthException() {
        try {
            DanceClass dc = new DanceClass("Hip Hop", 1500, "", "Open");
            fail("StringLengthException expected");
        } catch (StringLengthException e) {
            // pass
        } catch (TimeOutOfBoundsException e) {
            fail("TimeOutOfBoundException not expected");
        }
    }

    @Test
    public void testDanceClassConstructorDifficultyLengthException() {
        try {
            DanceClass dc = new DanceClass("Hip Hop", 1500, "Jerome", "");
            fail("StringLengthException expected");
        } catch (StringLengthException e) {
            // pass
        } catch (TimeOutOfBoundsException e) {
            fail("TimeOutOfBoundException not expected");
        }
    }

    @Test
    public void testDanceClassConstructorNegativeTimeException() {
        try {
            DanceClass dc = new DanceClass("Hip Hop", -1, "Jerome", "Open");
            fail("TimeOutOfBoundException expected");
        } catch (StringLengthException e) {
            fail("StringLengthException not expected");
        } catch (TimeOutOfBoundsException e) {
            // pass
        }
    }

    @Test
    public void testDanceClassConstructorGreaterThan2400TimeException() {
        try {
            DanceClass dc = new DanceClass("Hip Hop", 2401, "Jerome", "Open");
            fail("TimeOutOfBoundException expected");
        } catch (StringLengthException e) {
            fail("StringLengthException not expected");
        } catch (TimeOutOfBoundsException e) {
            // pass
        }
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

        assertEquals("Krump", danceClass1.getClassName());
        assertEquals(1930, danceClass1.getTime());
        assertEquals("Jane", danceClass1.getTeacherName());
        assertEquals("Open", danceClass1.getDifficultyLevel());
    }
    @Test
    public void testRegisterStudentEmptyClass() {
        try {
            assertTrue(danceClass1.registerStudent(jerome));
            assertEquals(1,danceClass1.sizeOfClass());
            assertTrue(danceClass1.isStudentRegistered(jerome));
        } catch (StudentAlreadyRegisteredException e) {
            fail("Exception not expected here");
        }
    }

    @Test
    public void testRegisterStudentAlreadyInClass() throws StudentAlreadyRegisteredException {
            danceClass1.registerStudent(jerome);
        try {
            danceClass1.registerStudent(jerome);
            fail("Exception expected here");
        } catch (StudentAlreadyRegisteredException e) {
            //pass
        }
        assertEquals(1,danceClass1.sizeOfClass());
        assertTrue(danceClass1.isStudentRegistered(jerome));
    }

    @Test
    public void testRemoveStudentOneStudent() throws StudentAlreadyRegisteredException {
        assertTrue(danceClass1.registerStudent(jon));
        danceClass1.removeStudent(jon);

        assertEquals(0, danceClass1.getRegisteredStudents().size());
    }

    @Test
    public void testRemoveMultipleStudents() throws StudentAlreadyRegisteredException {
        assertTrue(danceClass1.registerStudent(jon));
        assertTrue(danceClass1.registerStudent(bella));
        assertTrue(danceClass1.registerStudent(josh));

        danceClass1.removeStudent(jon);
        danceClass1.removeStudent(josh);

        assertEquals(1, danceClass1.sizeOfClass());
        assertEquals(bella, danceClass1.getRegisteredStudents().get(0));
    }

    @Test
    public void testRegisterStudentMultipleStudentsMaxDifferentStudent() throws StudentAlreadyRegisteredException {
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

        try {
            assertFalse(danceClass1.registerStudent(jerome));
        } catch (StudentAlreadyRegisteredException e) {
            fail("Exception not expected");
        }
        assertFalse(danceClass1.getRegisteredStudents().contains(jerome));
        assertEquals(15, danceClass1.sizeOfClass());
    }

    @Test
    public void testRegisterStudentMultipleStudentsMaxDuplicateStudent() throws StudentAlreadyRegisteredException {
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

        try {
            assertFalse(danceClass1.registerStudent(petra));
            fail("Student already registered, exception expected");
        } catch (StudentAlreadyRegisteredException e) {
            // pass
        }
        assertTrue(danceClass1.getRegisteredStudents().contains(petra));
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
    public void testAddDanceClassSameTime() throws StringLengthException, TimeOutOfBoundsException {
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