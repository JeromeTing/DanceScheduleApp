package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import model.WeeklySchedule;
import model.DanceClass;
import model.Day;

public class Table extends JPanel {
    WeeklySchedule weeklySchedule = new WeeklySchedule();


    public void testingAddClasses() {
        DanceClass danceClass1 = new DanceClass("Hip Hop", 1930, "Erik", "Advanced");
        DanceClass danceClass2 = new DanceClass("Krump", 1230, "Jane", "Open");
        DanceClass danceClass3 = new DanceClass("Popping", 2030, "Eric", "Intermediate");
        DanceClass danceClass4 = new DanceClass("Tutting", 1000, "Shazam", "Intro");
        DanceClass danceClass5 = new DanceClass("Waacking", 1600, "Amie", "Advanced");
        DanceClass danceClass6 = new DanceClass("Kpop", 1430, "Marvin", "Open");
        Day monday = weeklySchedule.getWeeklySchedule().get(0);
        monday.addDanceClass(danceClass1);
        monday.addDanceClass(danceClass2);
        monday.addDanceClass(danceClass3);
        monday.addDanceClass(danceClass4);
        monday.addDanceClass(danceClass5);
        monday.addDanceClass(danceClass5);
        monday.addDanceClass(danceClass6);
    }


    public Table() {
        super(new GridLayout(14, 0));

        String[] col = {"Time", "Class Name", "Difficulty", "Teacher", "Number of Students"};

        DefaultTableModel mondayTable = new DefaultTableModel(col,0);
        testingAddClasses();
        addDanceClassesToTable(0,mondayTable);

        DefaultTableModel tuesdayTable = new DefaultTableModel(col,0);
        addDanceClassesToTable(1,tuesdayTable);

        DefaultTableModel wednesdayTable = new DefaultTableModel(col,0);
        addDanceClassesToTable(2,wednesdayTable);

        DefaultTableModel thursdayTable = new DefaultTableModel(col,0);
        addDanceClassesToTable(3,thursdayTable);

        DefaultTableModel fridayTable = new DefaultTableModel(col,0);
        addDanceClassesToTable(4,fridayTable);

        DefaultTableModel saturdayTable = new DefaultTableModel(col,0);
        addDanceClassesToTable(5,saturdayTable);

        DefaultTableModel sundayTable = new DefaultTableModel(col,0);
        addDanceClassesToTable(6,sundayTable);

        createJTableForWeek(mondayTable,tuesdayTable,wednesdayTable,thursdayTable,fridayTable,saturdayTable,sundayTable);
    }

    public void createJTableForWeek(DefaultTableModel m, DefaultTableModel t, DefaultTableModel w,
                                    DefaultTableModel th, DefaultTableModel f, DefaultTableModel sa,
                                    DefaultTableModel su) {
        JTable mondayTable = new JTable(m);
        JTable tuesdayTable = new JTable(t);
        JTable wednesdayTable = new JTable(w);
        JTable thursdayTable = new JTable(th);
        JTable fridayTable = new JTable(f);
        JTable saturdayTable = new JTable(sa);
        JTable sundayTable = new JTable(su);

        initializeTable(mondayTable);
        initializeTable(tuesdayTable);
        initializeTable(wednesdayTable);
        initializeTable(thursdayTable);
        initializeTable(fridayTable);
        initializeTable(saturdayTable);
        initializeTable(sundayTable);

        addPanes(mondayTable, tuesdayTable, wednesdayTable, thursdayTable, fridayTable, saturdayTable, sundayTable);

    }

    public void initializeTable(JTable table) {
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
    }

    public void addPanes(JTable m, JTable t, JTable w, JTable th, JTable f, JTable sa, JTable su) {
        JScrollPane mondayScrollPane = new JScrollPane(m);
        JScrollPane tuesdayScrollPane = new JScrollPane(t);
        JScrollPane wednesdayScrollPane = new JScrollPane(w);
        JScrollPane thursdayScrollPane = new JScrollPane(th);
        JScrollPane fridayScrollPane = new JScrollPane(f);
        JScrollPane saturdayScrollPane = new JScrollPane(sa);
        JScrollPane sundayScrollPane = new JScrollPane(su);

       // GridBagConstraints tableConstraints = new GridBagConstraints();
       // GridBagConstraints labelConstraints = new GridBagConstraints();
       // initializeConstraintsGridBag(tableConstraints, labelConstraints);

        add(new JLabel("Monday"), JLabel.CENTER);
        add(mondayScrollPane);
        add(new JLabel("Tuesday"));
        add(tuesdayScrollPane);
        add(new JLabel("Wednesday"));
        add(wednesdayScrollPane);
        add(new JLabel("Thursday"));
        add(thursdayScrollPane);
        add(new JLabel("Friday"));
        add(fridayScrollPane);
        add(new JLabel("Saturday"));
        add(saturdayScrollPane);
        add(new JLabel("Sunday"));
        add(sundayScrollPane);
    }

    public void initializeConstraintsGridBag(GridBagConstraints table, GridBagConstraints label) {

    }

    public void addDanceClassesToTable(int index, DefaultTableModel tableModel) {
        Day day = weeklySchedule.getWeeklySchedule().get(index);
        for (DanceClass dc: day.getDaySchedule()) {

            Object[] row = {new Integer(dc.getTime()), dc.getClassName(), dc.getDifficultyLevel(),
                    dc.getTeacherName(), new Integer(dc.getRegisteredStudents().size())};

            tableModel.addRow(row);
        }
    }



    public static void renderAndDisplayGUI() {
        JFrame frame = new JFrame("Weekly Dance Schedule");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Table newWeekPane = new Table();
        newWeekPane.setOpaque(true);
        frame.setContentPane(newWeekPane);

        frame.pack();
        frame.setVisible(true);
    }
}
