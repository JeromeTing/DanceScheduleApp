package ui;

// Dance schedule application
// Parts of this class was based and inspired by the TellerApp and GymKioskApp

import model.DanceClass;
import model.Day;
import model.Student;
import model.WeeklySchedule;

import java.util.Scanner;

// Dance schedule application
public class DanceScheduleApp {

    private WeeklySchedule week;
    private Scanner input;

    //EFFECTS: runs the dance schedule app
    public DanceScheduleApp() {
        runDanceSchedule();
    }

    // MODIFIES: this
    // EFFECTS: processes the user input for the initial start instructions
    private void runDanceSchedule() {
        boolean isRunning = true;
        String command;

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
        System.out.println("\nSee you Anytime Anywhere Anyplace");
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
        System.out.println("\tc: Change Class Details");
        System.out.println("\tx: Remove Class");
        System.out.println("\tr: Register/Remove Student from a class");
        System.out.print("\tq: Quit Application\n");
    }

    // MODIFIES: this
    // EFFECTS: processes the users command or will notify that an invalid command was entered
    private void processCommand(String command) {
        if (command.equals("s")) {
            displaySchedule();
        } else if (command.equals("a")) {
            addClassToSchedule();
        } else if (command.equals("c")) {
            changeClassDetails();
        } else if (command.equals("r")) {
            processStudentCommand();
        } else if (command.equals("x")) {
            removeClassFromSchedule();
        } else {
            System.out.println("Try a valid command");
        }

    }

    // MODIFIES: this
    // EFFECTS: processes the command to register/remove a student from a class, if invalid nothing occurs and goes
    //          back to main start instructions
    private void processStudentCommand() {
        System.out.println("Type:");
        System.out.println("\t'register' to register a student from a class");
        System.out.println("\t'remove' to remove a student from a class");

        String studentCommand = input.next().toLowerCase();

        if (studentCommand.equals("register")) {
            registerStudentToClass();
        } else if (studentCommand.equals("remove")) {
            removeStudentFromClass();
        }
    }

    // EFFECTS: prints the Weekly schedule
    private void displaySchedule() {
        for (Day day : week.getWeeklySchedule()) {

            System.out.println("\n" + day.getDayName() + ":" + "\n");

            for (DanceClass c : day.getDaySchedule()) {
                System.out.println(c.getDifficultyLevel() + " " + c.getClassName());
                System.out.println("Time: " + c.getTime());
                System.out.println("Teacher: " + c.getTeacherName());
                System.out.println("Number of students registered: " + c.sizeOfClass());
                System.out.println("\n");
            }
        }
    }

    // EFFECT: prints out the current weekly schedule for Monday to Friday (with all the classes listed in each)
    private void displayDaySchedule(Day day) {
        System.out.println("\n" + day.getDayName() + ":" + "\n");

        for (DanceClass c : day.getDaySchedule()) {
            System.out.println(c.getDifficultyLevel() + " " + c.getClassName());
            System.out.println("Time: " + c.getTime());
            System.out.println("Teacher: " + c.getTeacherName());
            System.out.println("Number of students registered: " + c.sizeOfClass());
            System.out.println("\n");
        }
    }

    // EFFECTS: prints out the students registered in the class
    private void displayStudents(DanceClass danceClass) {
        System.out.println("Student's registered in the class:");
        for (Student s : danceClass.getRegisteredStudents()) {
            System.out.println("\t" + s.getName() + ": " + displayMembershipNumberStatus(s));
        }
    }

    // EFFECTS: returns membership number as string if the student is a member, else return "No membership"
    private String displayMembershipNumberStatus(Student s) {
        int membershipNum;
        if (s.getMembershipNumber() == 0) {
            return "No membership";
        } else {
            membershipNum = s.getMembershipNumber();
            return String.valueOf(membershipNum);
        }
    }

    // MODIFIES: this
    // EFFECT: adds a dance class to the weekly schedule for a specific day
    private void addClassToSchedule() {
        Day dayName;
        dayName = pickDay();
        DanceClass danceClass = addClass();
        dayName.addDanceClass(danceClass);
        System.out.println("Class added successfully!");
    }

    // MODIFIES: this
    // EFFECT: changes a specified danceClass details (ClassName, Time, TeacherName, DifficultyLevel)
    private void changeClassDetails() {
        Day dayName = pickDay();
        displayDaySchedule(dayName);
        System.out.println("What time is the class you want to change?");
        DanceClass danceClass = findDanceClass(dayName);
        input.nextLine();

        danceClass.setClassName(addClassName("What is the name of the new class?"));
        danceClass.setTime(addTime("What is the new time?"));
        input.nextLine();
        danceClass.setTeacherName(addTeacherName("What is the new teacher's name?"));
        danceClass.setDifficultyLevel(addDifficultyLevel("What is the new difficulty level?"));

        System.out.println("The class has been updated!");
    }

    // EFFECT: Returns a dance class from a specified time
    private DanceClass findDanceClass(Day dayName) {

        int time = input.nextInt();

        while (true) {
            for (DanceClass d: dayName.getDaySchedule()) {
                if (time == d.getTime()) {
                    return d;
                }
            }
            System.out.println("Can't find that class, try again");
        }
    }

    // MODIFIES: this
    // EFFECT: removes a dance class from the weekly schedule from a specified Day and time
    private void removeClassFromSchedule() {
        Day dayName = pickDay();
        displayDaySchedule(dayName);
        System.out.println("What time is the class? you want to remove?");
        DanceClass danceClass = findDanceClass(dayName);

        dayName.removeDanceClass(danceClass);
        System.out.println("Class has been successfully removed!");
    }

    // EFFECT: Takes a user string and returns the matched day (Monday to Sunday)
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
        System.out.println("What day of the week is the class?");
        return input.nextLine();
    }

    // EFFECT: returns a dance class with user specified input of a time
    private DanceClass addClass() {
        String className = addClassName("What is the name of the class?");
        int time = -1;
        
        while (time < 0 || time > 2400) {
            time = addTime("What time is this class?");
            if (time < 0 || time > 2400) {
                System.out.println("Please specify a time between 0 to 2400");
            }
        }

        input.nextLine();
        String teacherName = addTeacherName("What is the teacher's name?");
        String difficultyLvl = addDifficultyLevel("What is the difficulty of the class?");

        return new DanceClass(className, time, teacherName, difficultyLvl);
        //
    }

    // EFFECT: takes the user input and returns that string for the class name
    private String addClassName(String s) {
        System.out.println(s + " (Hip Hop, Waacking etc)");
        return input.nextLine();
    }

    // EFFECT: takes the user input and returns time as an into between 0 to 2400
    private int addTime(String s) {
        System.out.println(s + " (between 0 to 2400)");
        return input.nextInt();
    }

    // EFFECT: takes the user input and returns that string for a teacher's name
    private String addTeacherName(String s) {
        System.out.println(s);
        return input.nextLine();
    }

    // EFFECT: takes the user input and returns that string for the level difficulty of the class
    private String addDifficultyLevel(String s) {
        System.out.println(s + " (Open, Intro, Intermediate, or Advanced)");
        return input.nextLine();
    }

    // EFFECT: returns the name of a student
    private String inputStudentName() {
        input.nextLine();
        System.out.println("What is the student's name?");
        return input.nextLine();
    }

    // MODIFIES: this
    // EFFECT: Creates a new student and registers them into a dance class
    private void registerStudentToClass() {
        Student student;

        String studentName = inputStudentName();

        displayMembershipInstructions();

        int membership = handleMembershipCommand();

        if (membership == -1) {
            System.out.println("What is the membership number?");
            membership = input.nextInt();
        }
        student = new Student(studentName, membership);
        registerStudent(student);
    }

    // MODIFIES: this
    // EFFECT: removes a student from a danceClass by finding their membership number, if no student is registered
    //          in the class, then nothing occurs
    private void removeStudentFromClass() {
        Day dayName = pickDay();
        displayDaySchedule(dayName);
        if (!dayName.getDaySchedule().isEmpty()) {
            System.out.println("What time is this class?");
            DanceClass danceClass = findDanceClass(dayName);

            if (danceClass.sizeOfClass() != 0) {
                Student student = findStudentInClass(danceClass);
                danceClass.removeStudent(student);

                System.out.println("Student removed!");
            }
        }
    }

    private Student findStudentInClass(DanceClass danceClass) {
        displayStudents(danceClass);
        while (true) {
            System.out.println("What is the membership number of the student you want to remove?");
            int membershipNum = input.nextInt();
            for (Student s : danceClass.getRegisteredStudents()) {
                if (s.getMembershipNumber() == membershipNum) {
                    return s;
                }
                System.out.println("No student of that number found");
            }
        }
    }


    // MODIFIES: this
    // EFFECT: Registers a student to a dance class
    private void registerStudent(Student student) {
        input.nextLine();

        String dayOfWeek = inputDay().toLowerCase();
        int index = matchDayWithInt(dayOfWeek);
        Day dayName = week.getWeeklySchedule().get(index);

        displayDaySchedule(dayName);
        if (!dayName.getDaySchedule().isEmpty()) {
            System.out.println("What time is this class?");
            DanceClass danceClass = findDanceClass(dayName);
            danceClass.registerStudent(student);

            System.out.println("Student registered!");
            displayStudents(danceClass);
        }

        if (dayName.getDaySchedule().isEmpty()) {
            System.out.println("No classes are on this day!");
        }
    }


    // EFFECT: displays instructions about whether they are a member
    private void displayMembershipInstructions() {
        System.out.println("Are they a member?");
        System.out.println("y: Yes \t n: No");
    }

    // EFFECT: returns the 0 if "n" is pressed, and -1 if "y" is pressed
    private int handleMembershipCommand() {
        String command;
        int userInput = -1;
        command = input.next();
        command = command.toLowerCase();

        if (command.equals("n")) {
            return 0;
        } else {
            return userInput;
        }
    }

    // EFFECT: returns an int that is a match for the index of the week (0 to 6), if no strings match then returns -1
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
