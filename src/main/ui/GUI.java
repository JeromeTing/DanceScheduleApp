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
import model.exceptions.StringLengthException;
import model.exceptions.StudentAlreadyRegisteredException;
import model.exceptions.TimeOutOfBoundsException;
import persistence.JsonReader;
import persistence.JsonWriter;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

// Graphic user interface for Weekly Dance Schedule App
// Saving and loading portions based on (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
public class GUI extends JPanel {
    private String jsonStore = "./data/weeklyschedule.json";            //String of path for saving/loading
    private String rattleSnake = "./data/Rucucu.wav";                   // String of path for Rucucu sound
    private String absolutely = "./data/Absolutely2.wav";               // String of path for Absolutely sound
    private String ding = "./data/ding.wav";                            // String of path for ding sound
    private String capture = "./data/CaptureSound.wav";                 // String of path for the capture sound

    private JsonWriter jsonWriter = new JsonWriter(jsonStore);
    private JsonReader jsonReader = new JsonReader(jsonStore);

    private WeeklySchedule weeklySchedule = new WeeklySchedule();       // Creates a new weekly schedule

    private DefaultTableModel monTable;                                 // DefaultTableModel for Monday's schedule
    private DefaultTableModel tuesTable;                                // DefaultTableModel for Tuesday's schedule
    private DefaultTableModel wedTable;                                // DefaultTableModel for Wednesday's schedule
    private DefaultTableModel thurTable;                               // DefaultTableModel for Thursday's schedule
    private DefaultTableModel friTable;                                // DefaultTableModel for Friday's schedule
    private DefaultTableModel satTable;                                // DefaultTableModel for Saturday's schedule
    private DefaultTableModel sunTable;                                // DefaultTableModel for Sunday's schedule

    private JButton addButton;                      // JButton for the add dance class button
    private JButton removeButton;                   // JButton for the remove dance class button
    private JButton saveButton;                     // JButton for the save button
    private JButton loadButton;                     // JButton for the load button


    // EFFECT: constructs a new graphical user interface with a gridbaglayout and adds panes to the GUI
    private GUI() {
        super(new GridBagLayout());
        addPanes();
    }

    // MODIFIES: c
    // EFFECTS: Adds the grid bag constraints for the labels (of Weekdays)
    private void addLabelConstraints(GridBagConstraints c, int i) {
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 1;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = i;
    }

    // MODIFIES: c
    // EFFECTS: Adds the grid bag constraints for the tables (of the Weekdays)
    private void addTableConstraints(GridBagConstraints c, int i) {
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 4;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = i;
    }

    // MODIFIES: table
    // EFFECT: Initializes the JTable (dimensions, scrollable)
    private void initializeTable(JTable table) {
        table.setPreferredScrollableViewportSize(new Dimension(600, 70));
        table.setFillsViewportHeight(true);
    }

    // MODIFIES: this
    // EFFECTS: Adds the label and respective tables to the pane with specific grid bag constraints
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
                table.setEnabled(false); // makes tables non-editable
                initializeTable(table);
                JScrollPane scrollPane = new JScrollPane(table);
                addTableConstraints(tableConstraint, i);
                add(scrollPane, tableConstraint);
            }
        }
    }

    // EFFECT: Returns Weekday label depending on the integer, returns null if it's not an even number between [0
    // to 12]
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

    // EFFECTS: returns a JTable of the corresponding weekday based on an input integer
    // if it cannot find the table (where the integer is not an odd number [1,13] it will return null
    private JTable findTable(int i) {
        String[] col = {"Time", "Class Name", "Difficulty", "Teacher", "Number of Students"};

        if (i == 1) {
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

    // EFFECTS: Helper method that returns the table for Friday to Sunday based on an integer (Odd integer [0,13])
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

    // MODIFIES: this
    // EFFECTS: Adds the dance classes to their respective table row by row with corresponding information
    // for each day schedule in chronological order
    private void addDanceClassesToTable(int index, DefaultTableModel tableModel) {
        Day day = weeklySchedule.getWeeklySchedule().get(index);
        for (DanceClass dc: day.getDaySchedule()) {

            Object[] row = {dc.getTime(), dc.getClassName(), dc.getDifficultyLevel(),
                    dc.getTeacherName(), dc.getRegisteredStudents().size()};

            tableModel.addRow(row);
        }
    }

    // EFFECTS: Returns a JPanel with buttons added to it (add, remove, save load)
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

    // MODIFIES: this
    // EFFECTS: processes the actions of clicking the add a dance class by creating a new frame and the
    // corresponding fields
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

    // MODIFIES: this
    // EFFECTS: processes the actions of clicking the remove a dance class button by creating a new frame and the
    // corresponding fields
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

    // MODIFIES: this
    // EFFECTS: processes the actions of clicking the save button by saving the dance schedule to file
    // plays the capture sound when save occurs
    private void processSaveButton() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(weeklySchedule);
                    jsonWriter.close();
                } catch (FileNotFoundException exception) {
                    System.out.println("Unable to write to file: " + jsonStore);
                }
                playSound(capture);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: processes the actions of clicking the load button by loading the dance schedule from file
    // plays absolutely sound clip when load occurs
    private void processLoadButton() {
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    weeklySchedule = jsonReader.read();

                } catch (IOException exception) {
                    System.out.println("Unable to read from file: " + jsonStore);
                } catch (StringLengthException | StudentAlreadyRegisteredException
                        | TimeOutOfBoundsException stringLengthException) {
                    stringLengthException.printStackTrace();
                }
                for (int i = 0; i < 7; i++) {
                    updateTable(i);
                }
                playSound(absolutely);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: Initializes the remove dance class window frame and all its fields, also process the action of
    // clicking the submit button, plays rattle snake sound when submit is clicked for removed frame
    private void initializeRemoveFrame(JFrame frame) {
        frame.setLayout(new GridLayout(5,0));
        JTextField weekday = new JTextField();
        JTextField time = new JTextField();
        JButton submitButton = new JButton("Submit");

        frame.add(new JLabel("Day of the Week (Monday, Tuesday etc.)"));
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
                playSound(rattleSnake);
                frame.dispose();
            }
        });

    }

    // EFFECT: Given a Day, returns a dance class from a specified time
    private DanceClass findDanceClass(Day dayName, int time) {
        for (DanceClass d : dayName.getDaySchedule()) {
            if (time == d.getTime()) {
                return d;
            }
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: Initializes the add dance class window frame and all its fields
    private void initializeAddFrame(JFrame frame) {
        frame.setLayout(new GridLayout(11,0));
        JTextField className = new JTextField();
        JTextField teacherName = new JTextField();
        JTextField difficultyLevel = new JTextField();
        JTextField time = new JTextField();
        JTextField weekday = new JTextField();
        JButton submitButton = new JButton("Submit");

        frame.add(new JLabel("Day of the Week (Monday, Tuesday etc.)"));
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
        switch (dayName) {
            case "monday":
                return 0;
            case "tuesday":
                return 1;
            case "wednesday":
                return 2;
            case "thursday":
                return 3;
            case "friday":
                return 4;
            case "saturday":
                return 5;
            case "sunday":
                return 6;
            default:
                return -1;
        }
    }

    // MODIFIES: this
    // EFFECT: Processes the submit button for when it is clicked in the add dance class frame
    // plays the ding sound effect when the remove submit button is clicked
    private void processSubmit(JButton submit, JTextField className,
                              JTextField teacherName, JTextField difficultyLevel,
                              JTextField time, JTextField day, JFrame frame) {
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int t = Integer.parseInt(time.getText());
                DanceClass danceClass = null;
                try {
                    danceClass = new DanceClass(className.getText(), t, teacherName.getText(),
                            difficultyLevel.getText());
                } catch (StringLengthException | TimeOutOfBoundsException stringLengthException) {
                    stringLengthException.printStackTrace();
                }
                int d = matchDayWithInt(day.getText().toLowerCase());
                Day weekday = weeklySchedule.getWeeklySchedule().get(d);
                weekday.addDanceClass(danceClass);
                updateTable(d);
                playSound(ding);
                frame.dispose();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: updates a specific Day schedule (based on the input integer) by clearing the table and re-adding
    // all the rows
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

    // EFFECT: Tries to play a sound from a specific path, catches exception if fails and
    // produces "ERROR PLAYING SOUND"
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

    // EFFECT: Renders and displays the graphical user interface of the weekly dance schedule
    protected static void renderAndDisplayGUI() {
        JFrame frame = new JFrame("Weekly Dance Schedule");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints tableConstraints = new GridBagConstraints();
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        tableConstraints.gridy = 0;
        buttonConstraints.gridy = 1;

        GUI newWeekPane = new GUI();
        newWeekPane.setOpaque(true);
        frame.add(newWeekPane,tableConstraints);
        frame.add(newWeekPane.setUpButtons(),buttonConstraints);

        frame.pack();
        frame.setVisible(true);
    }
}
