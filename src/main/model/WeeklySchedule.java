package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents the schedule for the entire week (Monday to Friday)
public class WeeklySchedule implements Writable {

    private final List<Day> weeklySchedule;         // List of Days in a week

    //EFFECTS: creates a weekly schedule that is an array, where the days of the week are added
    //          by default (Monday to Friday)
    public WeeklySchedule() {
        Day monday = new Day("Monday");
        Day tuesday = new Day("Tuesday");
        Day wednesday = new Day("Wednesday");
        Day thursday = new Day("Thursday");
        Day friday = new Day("Friday");
        Day saturday = new Day("Saturday");
        Day sunday = new Day("Sunday");

        weeklySchedule = new ArrayList<>();
        weeklySchedule.add(monday);
        weeklySchedule.add(tuesday);
        weeklySchedule.add(wednesday);
        weeklySchedule.add(thursday);
        weeklySchedule.add(friday);
        weeklySchedule.add(saturday);
        weeklySchedule.add(sunday);
    }

    //getters

    public List<Day> getWeeklySchedule() {
        return weeklySchedule;
    }

    @Override
    // EFFECTS: creates a json object and puts the days to the json object and returns it
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("days", daysToJson());
        return json;
    }

    private JSONArray daysToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Day d: weeklySchedule) {
            jsonArray.put(d.toJson());
        }

        return jsonArray;
    }
}
