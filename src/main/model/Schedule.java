package model;

import java.util.ArrayList;
import java.util.List;

public class Schedule {

    private List<DanceClass> schedule;

    //EFFECTS: creates an empty collection of
    public Schedule() {
        schedule = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS:
    public void addDanceClass() {
    //stub
    }

    // REQUIRES: dance class to be in the schedule
    // MODIFIES: this
    // EFFECTS:
    public void removeDanceClass() {
    //stub
    }

    //EFFECTS:
    public Schedule currentSchedule() {
        return null; //stub
    }
}
