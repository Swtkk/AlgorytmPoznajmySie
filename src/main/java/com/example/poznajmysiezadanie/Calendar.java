package com.example.poznajmysiezadanie;

import java.util.ArrayList;
import java.util.List;

public class Calendar {
    private TimeSchedule working_hours;

    private List<TimeSchedule> planned_meeting;


    public TimeSchedule getWorkingHours() {
        return working_hours;
    }

    public List<TimeSchedule> getPlannedMeeting() {
        if(planned_meeting ==null){
            planned_meeting = new ArrayList<>();
        }
        return planned_meeting;
    }
}
