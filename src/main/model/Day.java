package model;

import java.util.ArrayList;
import java.util.List;

public class Day {
    private List<DanceClass> daySchedule;
    private String dayName;
    private DanceClass danceClass;

    // EFFECT: creates an empty list of dance classes in a day
    public Day(String day) {
        daySchedule = new ArrayList<>();
        dayName = day;
    }

    // REQUIRES: dance class cannot be already in the schedule for the day and cannot have the same time
    //           as another class
    // MODIFIES: this
    // EFFECTS: adds a dance class to a particular day in order
    public void addDanceClass(DanceClass danceClass) {
        if (daySchedule.size() == 0) {
            daySchedule.add(danceClass);
        } else if (daySchedule.get(0).getTime() > danceClass.getTime()) {
            daySchedule.add(0, danceClass);
        } else if (daySchedule.get(daySchedule.size() - 1).getTime() < danceClass.getTime()) {
            daySchedule.add(daySchedule.size(), danceClass);
        } else {
            for (DanceClass c: daySchedule) {
                if (c.getTime() > danceClass.getTime()) {
                    int index = daySchedule.indexOf(c);
                    daySchedule.add(index, danceClass);
                    break;
                }
            }
        }
    }

    // REQUIRES: dance class must be in the schedule
    // MODIFIES: this
    // EFFECTS: removes a dance class from a particular day
    public void removeDanceClass(DanceClass danceClass) {
        daySchedule.remove(danceClass);
    }

    // EFFECT: returns the number of classes in that day
    public int size() {
        return daySchedule.size();
    }

    // EFFECT: returns dance class at that index
    public DanceClass getDanceClass(int index) {
        return daySchedule.get(index);
    }
}
