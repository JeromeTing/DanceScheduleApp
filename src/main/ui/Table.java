package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import model.WeeklySchedule;
import model.DanceClass;
import model.Day;

public class Table extends JPanel {
    WeeklySchedule weeklySchedule = new WeeklySchedule();
    JTable monTable = new JTable();
    JTable tuesTable = new JTable();
    JTable wedTable = new JTable();
    JTable thurTable = new JTable();
    JTable friTable = new JTable();
    JTable satTable = new JTable();
    JTable sunTable = new JTable();



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
        monday.addDanceClass(danceClass6);
    }


    public Table() {
        super(new GridBagLayout());
        addPanes();
    }

    public void addLabelConstraints(GridBagConstraints c, int i) {
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 10;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = i;
    }

    public void addTableConstraints(GridBagConstraints c, int i) {
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = i;
    }

    public void initializeTable(JTable table) {
        table.setPreferredScrollableViewportSize(new Dimension(600, 70));
        table.setFillsViewportHeight(true);
    }

    public void addPanes() {
        JTable table;
        GridBagConstraints labelConstraint = new GridBagConstraints();
        GridBagConstraints tableConstraint = new GridBagConstraints();

        for (int i = 0; i < 14; i++) {
            if (i % 2 == 0) {
                String label = findLabel(i);
                addLabelConstraints(labelConstraint, i);
                add(new JLabel(label), labelConstraint);
            } else {
                table = findTable(i);
                initializeTable(table);
                JScrollPane scrollPane = new JScrollPane(table);
                addTableConstraints(tableConstraint, i);
                add(scrollPane, tableConstraint);
            }
        }

    }

    public String findLabel(int i) {
        if (i == 0) {
            return "Monday";
        } else if (i == 2) {
            return "Tuesday";
        } else if (i == 4) {
            return "Wednesday";
        } else if (i == 6) {
            return "Thursday";
        } else if (i == 8) {
            return "Friday";
        } else if (i == 10) {
            return "Saturday";
        } else if (i == 12) {
            return "Sunday";
        } else {
            return null;
        }
    }

    public JTable findTable(int i) {
        String[] col = {"Time", "Class Name", "Difficulty", "Teacher", "Number of Students"};
        DefaultTableModel defaultTable = new DefaultTableModel(col,0);

        if (i == 1) {
            testingAddClasses();
            addDanceClassesToTable(0,defaultTable);
            monTable = new JTable(defaultTable);
            return monTable;
        } else if (i == 3) {
            addDanceClassesToTable(1,defaultTable);
            tuesTable = new JTable(defaultTable);
            return tuesTable;
        } else if (i == 5) {
            addDanceClassesToTable(2,defaultTable);
            wedTable = new JTable(defaultTable);
            return wedTable;
        } else if (i == 7) {
            addDanceClassesToTable(3,defaultTable);
            thurTable = new JTable(defaultTable);
            return thurTable;
        } else {
            return findTableFriToSun(i);
        }
    }

    public JTable findTableFriToSun(int i) {
        JTable table;
        String[] col = {"Time", "Class Name", "Difficulty", "Teacher", "Number of Students"};
        DefaultTableModel defaultTable = new DefaultTableModel(col,0);

        if  (i == 9) {
            addDanceClassesToTable(4,defaultTable);
            friTable = new JTable(defaultTable);
            return friTable;
        } else if (i == 11) {
            addDanceClassesToTable(5,defaultTable);
            satTable = new JTable(defaultTable);
            return satTable;
        } else if (i == 13) {
            addDanceClassesToTable(6,defaultTable);
            sunTable = new JTable(defaultTable);
            return sunTable;
        } else {
            return null;
        }
    }

    public void addDanceClassesToTable(int index, DefaultTableModel tableModel) {
        Day day = weeklySchedule.getWeeklySchedule().get(index);
        for (DanceClass dc: day.getDaySchedule()) {

            Object[] row = {dc.getTime(), dc.getClassName(), dc.getDifficultyLevel(),
                    dc.getTeacherName(), dc.getRegisteredStudents().size()};

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
