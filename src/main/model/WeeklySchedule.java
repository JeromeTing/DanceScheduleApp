package model;

import java.util.ArrayList;
import java.util.List;

public class WeeklySchedule {

    private List<Day> weeklySchedule;
    private Day monday;
    private Day tuesday;
    private Day wednesday;
    private Day thursday;
    private Day friday;
    private Day saturday;
    private Day sunday;

    //EFFECTS: creates an empty collection of
    public WeeklySchedule() {
        monday = new Day("Monday");
        tuesday = new Day("Tuesday");
        wednesday = new Day("Wednesday");
        thursday = new Day("Thursday");
        friday = new Day("Friday");
        saturday = new Day("Saturday");
        sunday = new Day("Sunday");

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

}
