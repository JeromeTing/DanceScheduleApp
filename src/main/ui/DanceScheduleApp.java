package ui;

// Dance schedule application

import model.DanceClass;
import model.Day;
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
        System.out.printf("\tq: Quit Application\n");
    }

    private void processCommand(String command) {
        if (command.equals("s")) {
            displaySchedule();
        } else if (command.equals("a")) {
            pickDay();
        } else if (command.equals("r")) {
            registerStudent();
        } else if (command.equals("x")) {
            removeClass();
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

    // MODIFIES: this
    // EFFECT: Picks day and adds the dance class to the week
    private void pickDay() {
        input.nextLine();
        String dayOfWeek = inputDay().toLowerCase();
        int index = matchDayWithInt(dayOfWeek);
        Day dayName = week.getWeeklySchedule().get(index);

        DanceClass danceClass = addClass();
        dayName.addDanceClass(danceClass);
    }

    private String inputDay() {
        System.out.println("What day would you like to add the class?");
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

    private String addClassName() {
        System.out.println("What is the name of the class (Hip Hop, Waacking etc)");
        return input.nextLine();
    }

    private int addTime() {
        System.out.println("Add a time as an integer between 0 to 2400");
        return input.nextInt();
    }

    private String addTeacherName() {
        System.out.println("Who is teaching this class?");
        return input.nextLine();
    }

    private String addDifficultyLevel() {
        System.out.println("Please state whether it is Open, Intro, Intermediate, or Advanced");
        return input.nextLine();
    }

    private void registerStudent() {

    }

    private void removeClass() {

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
