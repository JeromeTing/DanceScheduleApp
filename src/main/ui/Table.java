package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;

import model.WeeklySchedule;
import model.DanceClass;
import model.Day;
import persistence.JsonReader;
import persistence.JsonWriter;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Table extends JPanel {
    private String jsonStore = "./data/weeklyschedule.json";
    private String rattleSnake = "./data/Rucucu.wav";
    private String absolutely = "./data/Absolutely2.wav";

    private JsonWriter jsonWriter = new JsonWriter(jsonStore);
    private JsonReader jsonReader = new JsonReader(jsonStore);

    private WeeklySchedule weeklySchedule = new WeeklySchedule();

    private DefaultTableModel monTable;
    private DefaultTableModel tuesTable;
    private DefaultTableModel wedTable;
    private DefaultTableModel thurTable;
    private DefaultTableModel friTable;
    private DefaultTableModel satTable;
    private DefaultTableModel sunTable;

    private JButton addButton;
    private JButton removeButton;
    private JButton saveButton;
    private JButton loadButton;




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


    private Table() {
        super(new GridBagLayout());
        addPanes();
    }

    private void addLabelConstraints(GridBagConstraints c, int i) {
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 1;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = i;
    }

    private void addTableConstraints(GridBagConstraints c, int i) {
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 4;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = i;
    }

    private void initializeTable(JTable table) {
        table.setPreferredScrollableViewportSize(new Dimension(600, 70));
        table.setFillsViewportHeight(true);
    }

    private void addPanes() {
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
                table.setEnabled(false);
                initializeTable(table);
                JScrollPane scrollPane = new JScrollPane(table);
                addTableConstraints(tableConstraint, i);
                add(scrollPane, tableConstraint);
            }
        }

    }

    private String findLabel(int i) {
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

    private JTable findTable(int i) {
        String[] col = {"Time", "Class Name", "Difficulty", "Teacher", "Number of Students"};

        if (i == 1) {
            //testingAddClasses();
            addDanceClassesToTable(0,monTable = new DefaultTableModel(col,0));
            return new JTable(monTable);
        } else if (i == 3) {
            addDanceClassesToTable(1,tuesTable = new DefaultTableModel(col, 0));
            return new JTable(tuesTable);
        } else if (i == 5) {
            addDanceClassesToTable(2,wedTable = new DefaultTableModel(col, 0));
            return new JTable(wedTable);
        } else if (i == 7) {
            addDanceClassesToTable(3,thurTable = new DefaultTableModel(col, 0));
            return new JTable(thurTable);
        } else {
            return findTableFriToSun(i);
        }
    }

    private JTable findTableFriToSun(int i) {
        String[] col = {"Time", "Class Name", "Difficulty", "Teacher", "Number of Students"};

        if  (i == 9) {
            addDanceClassesToTable(4,friTable = new DefaultTableModel(col, 0));
            return new JTable(friTable);
        } else if (i == 11) {
            addDanceClassesToTable(5,satTable = new DefaultTableModel(col, 0));
            return new JTable(satTable);
        } else if (i == 13) {
            addDanceClassesToTable(6,sunTable = new DefaultTableModel(col, 0));
            return new JTable(sunTable);
        } else {
            return null;
        }
    }

    private void addDanceClassesToTable(int index, DefaultTableModel tableModel) {
        Day day = weeklySchedule.getWeeklySchedule().get(index);
        for (DanceClass dc: day.getDaySchedule()) {

            Object[] row = {dc.getTime(), dc.getClassName(), dc.getDifficultyLevel(),
                    dc.getTeacherName(), dc.getRegisteredStudents().size()};

            tableModel.addRow(row);
        }
    }


    private JPanel setUpButtons() {
        addButton = new JButton("Add Dance Class");
        removeButton = new JButton("Remove Dance Class");
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");


        JPanel buttonArea = new JPanel();
        buttonArea.setLayout(new GridBagLayout());
        buttonArea.add(addButton);
        buttonArea.add(removeButton);
        buttonArea.add(saveButton);
        buttonArea.add(loadButton);

        processAddButton();
        processRemoveButton();
        processSaveButton();
        processLoadButton();

        return buttonArea;
    }

    private void processAddButton() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame addFrame = new JFrame();
                addFrame.setVisible(true);
                addFrame.setSize(new Dimension(250, 500));
                initializeAddFrame(addFrame);
            }
        });
    }

    private void processRemoveButton() {
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame removeFrame = new JFrame();
                removeFrame.setVisible(true);
                removeFrame.setSize(new Dimension(250, 500));
                initializeRemoveFrame(removeFrame);
            }
        });
    }

    private void processSaveButton() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(weeklySchedule);
                    jsonWriter.close();
                    System.out.println("Saved weekly dance schedule to " + jsonStore);
                } catch (FileNotFoundException exception) {
                    System.out.println("Unable to write to file: " + jsonStore);
                }
                playSound(rattleSnake);
            }
        });
    }

    private void processLoadButton() {
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    weeklySchedule = jsonReader.read();
                    System.out.println("Loaded weekly dance schedule from " + jsonStore);
                } catch (IOException exception) {
                    System.out.println("Unable to read from file: " + jsonStore);
                }
                for (int i = 0; i < 7; i++) {
                    updateTable(i);
                }
                playSound(absolutely);
            }
        });
    }


    private void initializeRemoveFrame(JFrame frame) {
        frame.setLayout(new GridLayout(5,0));
        JTextField weekday = new JTextField();
        JTextField time = new JTextField();
        JButton submitButton = new JButton("Submit");

        frame.add(new JLabel("What day is it on? (Monday)"));
        frame.add(weekday);
        frame.add(new JLabel("Time (Number from 0 - 2400"));
        frame.add(time);
        frame.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int t = Integer.parseInt(time.getText());
                int d = matchDayWithInt(weekday.getText().toLowerCase());
                Day day = weeklySchedule.getWeeklySchedule().get(d);
                DanceClass dc = findDanceClass(day, t);
                day.removeDanceClass(dc);
                updateTable(d);
                frame.dispose();

            }
        });

    }

    // EFFECT: Given a Day, returns a dance class from a specified time
    private DanceClass findDanceClass(Day dayName, int time) {

        while (true) {
            for (DanceClass d: dayName.getDaySchedule()) {
                if (time == d.getTime()) {
                    return d;
                }
            }
            System.out.println("Can't find that class, try again");
        }
    }

    private void initializeAddFrame(JFrame frame) {
        frame.setLayout(new GridLayout(11,0));
        JTextField className = new JTextField();
        JTextField teacherName = new JTextField();
        JTextField difficultyLevel = new JTextField();
        JTextField time = new JTextField();
        JTextField weekday = new JTextField();
        JButton submitButton = new JButton("Submit");

        frame.add(new JLabel("What day is it on? (Monday)"));
        frame.add(weekday);
        frame.add(new JLabel("Time (Number from 0 - 2400"));
        frame.add(time);
        frame.add(new JLabel("Class Name (Hip Hop, Popping etc)"));
        frame.add(className);
        frame.add(new JLabel("Teacher's Name"));
        frame.add(teacherName);
        frame.add(new JLabel("Difficulty Level (Open, Intro, Advanced, Intermediate)"));
        frame.add(difficultyLevel);
        frame.add(submitButton);
        processSubmit(submitButton, className, teacherName, difficultyLevel, time, weekday, frame);
    }

    // EFFECT: given a string, returns an int that is a match for the index of the week (0 to 6). If no strings
    //         match then returns -1
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
            return -1;
        }
    }

    private void processSubmit(JButton submit, JTextField className,
                              JTextField teacherName, JTextField difficultyLevel,
                              JTextField time, JTextField day, JFrame frame) {
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int t = Integer.parseInt(time.getText());
                DanceClass danceClass = new DanceClass(className.getText(), t, teacherName.getText(),
                        difficultyLevel.getText());
                int d = matchDayWithInt(day.getText().toLowerCase());
                Day weekday = weeklySchedule.getWeeklySchedule().get(d);
                weekday.addDanceClass(danceClass);
                updateTable(d);
                frame.dispose();

            }
        });
    }

    private void updateTable(int d) {
        if (d == 0) {
            monTable.setRowCount(0);
            addDanceClassesToTable(0, monTable);
        } else if (d == 1) {
            tuesTable.setRowCount(0);
            addDanceClassesToTable(1, tuesTable);
        } else if (d == 2) {
            wedTable.setRowCount(0);
            addDanceClassesToTable(2, wedTable);
        } else if (d == 3) {
            thurTable.setRowCount(0);
            addDanceClassesToTable(3, thurTable);
        } else if (d == 4) {
            friTable.setRowCount(0);
            addDanceClassesToTable(4, friTable);
        } else if (d == 5) {
            satTable.setRowCount(0);
            addDanceClassesToTable(5, satTable);
        } else {
            sunTable.setRowCount(0);
            addDanceClassesToTable(6, sunTable);
        }
    }

    public void playSound(String filepath) {
        InputStream sound;
        try {
            sound = new FileInputStream(new File(filepath));
            AudioStream audio = new AudioStream(sound);
            AudioPlayer.player.start(audio);
        } catch (Exception e) {
            System.out.println("ERROR PLAYING SOUND");
        }
    }

    protected static void renderAndDisplayGUI() {
        JFrame frame = new JFrame("Weekly Dance Schedule");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints tableConstraints = new GridBagConstraints();
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        tableConstraints.gridy = 0;
        buttonConstraints.gridy = 1;

        Table newWeekPane = new Table();
        newWeekPane.setOpaque(true);
        frame.add(newWeekPane,tableConstraints);
        frame.add(newWeekPane.setUpButtons(),buttonConstraints);

        frame.pack();
        frame.setVisible(true);
    }
}
