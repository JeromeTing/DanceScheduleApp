package persistence;

import model.WeeklySchedule;
import org.json.JSONObject;

import java.io.*;

// Represents a writer that writes JSON representation of a weekly dance schedule to file
// Code inspired and based on JsonSerializationDemo
//
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs a writer and writes it to a destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer
    //          if destination file cannot be opened for writing, throws FileNotFoundException
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of the WeeklySchedule to file
    public void write(WeeklySchedule ws) {
        JSONObject json = ws.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
