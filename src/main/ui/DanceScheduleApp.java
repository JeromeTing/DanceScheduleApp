package ui;

// Dance schedule application
// Parts of this class was based and inspired by the TellerApp and GymKioskApp

import model.DanceClass;
import model.Day;
import model.Student;
import model.WeeklySchedule;

import java.util.Scanner;

public class DanceScheduleApp {
    private static final String QUIT_COMMAND = "quit";

    private WeeklySchedule week;
    private Scanner input;

    //EFFECTS: runs the dance schedule app
    public DanceScheduleApp() {
        runDanceSchedule();
    }

    // MODIFIES: this
    // EFFECTS: processes the user input
    private void runDanceSchedule() {
        boolean isRunning = true;
        String command = null;

        initialize();

        while (isRunning) {
            displayStartInstructions();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                isRunning = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nAnytime Anywhere Anyplace");
    }

    // MODIFIES: this
    // EFFECTS: initializes the schedules for each day
    private void initialize() {
        week = new WeeklySchedule();
        input = new Scanner(System.in);
    }

    // EFFECTS: shows the start menu to the user
    private void displayStartInstructions() {
        System.out.println("\nWhat would you like to do?:");
        System.out.println("\ts: Show Schedule");
        System.out.println("\ta: Add Class");
        System.out.println("\tx: Remove Class");
        System.out.println("\tr: Register Student");
        System.out.print("\tq: Quit Application\n");
    }

    private void processCommand(String command) {
        if (command.equals("s")) {
            displaySchedule();
        } else if (command.equals("a")) {
            addClassToSchedule();
        } else if (command.equals("r")) {
            collectStudentInfo();
        } else if (command.equals("x")) {
            removeClassFromSchedule();
        } else {
            System.out.println("Try a valid command");
        }

    }

    // EFFECTS: prints the Weekly schedule

    private void displaySchedule() {
        for (Day day: week.getWeeklySchedule()) {

            System.out.println("\n" + day.getDayName() + ":" + "\n");

            for (DanceClass c: day.getDaySchedule()) {
                System.out.println(c.getDifficultyLevel() + " " + c.getClassName());
                System.out.println("Time: " + c.getTime());
                System.out.println("Teacher: " + c.getTeacherName());
                System.out.println("\n");
            }
        }
    }

    private void displayDaySchedule(Day day) {
        System.out.println("\n" + day.getDayName() + ":" + "\n");

        for (DanceClass c: day.getDaySchedule()) {
            System.out.println(c.getDifficultyLevel() + " " + c.getClassName());
            System.out.println("Time: " + c.getTime());
            System.out.println("Teacher: " + c.getTeacherName());
            System.out.println("Number of students registered:" + c.sizeOfClass());
            System.out.println("\n");
        }
    }

    private void addClassToSchedule() {
        Day dayName;
        dayName = pickDay();
        DanceClass danceClass = addClass();
        dayName.addDanceClass(danceClass);
    }

    private void removeClassFromSchedule() {
        Day dayName;
        DanceClass danceClass;
        int time;

        dayName = pickDay();
        System.out.println("What time is this class?");
        time = input.nextInt();
        danceClass = findClassByTime(dayName,time);
        dayName.removeDanceClass(danceClass);
    }

    // MODIFIES: this
    // EFFECT: Picks day and adds the dance class to the week
    private Day pickDay() {
        Day dayName;
        int index = -1;
        String inputDay;
        while (index == -1) {
            inputDay = input.nextLine();
            String dayOfWeek = inputDay().toLowerCase();
            index = matchDayWithInt(dayOfWeek);
        }

        dayName = week.getWeeklySchedule().get(index);
        return dayName;
    }

    // EFFECT: takes the user input and returns that string for the day of the week
    private String inputDay() {
        System.out.println("What day is this class on?");
        return input.nextLine();
    }

    // EFFECT: returns a dance class with user specified input
    private DanceClass addClass() {
        String className = addClassName();
        int time = addTime();
        input.nextLine();
        String teacherName = addTeacherName();
        String difficultyLvl = addDifficultyLevel();

        return new DanceClass(className, time, teacherName, difficultyLvl);
        //
    }

    // EFFECT: takes the user input and returns that string for the class name
    private String addClassName() {
        System.out.println("What is the name of the class (Hip Hop, Waacking etc)");
        return input.nextLine();
    }

    // EFFECT: takes the user input and returns time as an into between 0 to 2400
    private int addTime() {
        System.out.println("Add a time as an integer between 0 to 2400");
        return input.nextInt();
    }

    // EFFECT: takes the user input and returns that string for a teacher's name
    private String addTeacherName() {
        System.out.println("Who is teaching this class?");
        return input.nextLine();
    }

    // EFFECT: takes the user input and returns that string for the level difficulty of the class
    private String addDifficultyLevel() {
        System.out.println("Please state whether it is Open, Intro, Intermediate, or Advanced");
        return input.nextLine();
    }

    private void collectStudentInfo() {
        int membership = -1;
        Student student;

        System.out.println("What is the student's name?");
        String studentName = input.nextLine();
        displayMembershipInstructions();
        membership = handleMembershipCommand();

        if (membership == -1) {
            System.out.println("What is the membership number?");
            membership = input.nextInt();
        }
        student = new Student(studentName, membership);
        registerStudent(student);
    }

    private DanceClass userInputTime(Day dayName) {
        DanceClass danceClass = null;

        while (danceClass == null) {
            System.out.println("What time is this class?");
            int timeOfClass = input.nextInt();
            danceClass = findClassByTime(dayName, timeOfClass);

            if (danceClass != null) {
                return danceClass;
            }
        }
        System.out.println("Something went wrong!");
        return null;
    }

    private void registerStudent(Student student) {
        input.nextLine();

        String dayOfWeek = inputDay().toLowerCase();
        int index = matchDayWithInt(dayOfWeek);
        Day dayName = week.getWeeklySchedule().get(index);

        displayDaySchedule(dayName);
        if (!dayName.getDaySchedule().isEmpty()) {

            DanceClass danceClass = null;
            danceClass = userInputTime(dayName);
            danceClass.registerStudent(student);

            System.out.println("Student registered!");
        }

        if (dayName.getDaySchedule().isEmpty()) {
            System.out.println("No classes are on this day!");
        }
    }

    private DanceClass findClassByTime(Day day, int time) {
        for (DanceClass d: day.getDaySchedule()) {
            if (time == d.getTime()) {
                return d;
            }
        }
        System.out.println("Can't find that class, try again");
        return null;
    }

    private void displayMembershipInstructions() {
        System.out.println("Are they a member?");
        System.out.println("y: Yes \t n: No");
    }

    private int handleMembershipCommand() {
        String command;
        int userInput = -1;
        command = input.next();
        command = command.toLowerCase();

        if (command.equals("n")) {
            userInput = 0;
        } else if (command.equals("y")) {
            userInput = -1;
        }
        return userInput;
    }

    // EFFECT: returns an in that is the index of the week
    private int matchDayWithInt(String dayName) {
        if (dayName.equals("monday")) {
            return 0;
        } else if (dayName.equals("tuesday")) {
            return 1;
        } else if (dayName.equals("wednesday")) {
            return 2;
        } else if (dayName.equals("thursday")) {
            return 3;
        } else if (dayName.equals("friday")) {
            return 4;
        } else if (dayName.equals("saturday")) {
            return 5;
        } else if (dayName.equals("sunday")) {
            return 6;
        } else {
            System.out.println("You didn't write a valid day");
            return -1;
        }
    }
}
