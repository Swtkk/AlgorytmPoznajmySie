package com.example.poznajmysiezadanie;

import java.util.ArrayList;
import java.util.List;

public class CalendarMethods {

    public static int timeToMinutes(String time) {
        String[] tm = time.split(":");
        return Integer.parseInt(tm[0]) * 60 + Integer.parseInt(tm[1]);
    }

    public static String minutesToTime(int time) {
        return String.format("%02d:%02d", time / 60, time % 60);
    }

    public static List<TimeSchedule> findAllAvailableSlots(Calendar calendar, int meetingDuration) {
        List<TimeSchedule> slots = new ArrayList<>();

        int startWork = timeToMinutes(calendar.getWorkingHours().getStart());
        int endWork = timeToMinutes(calendar.getWorkingHours().getEnd());
        int start = startWork;

        for (TimeSchedule mt : calendar.getPlannedMeeting()) {
            if (mt != null) {
                int mtStart = timeToMinutes(mt.getStart());
                if (mtStart - start >= meetingDuration) {
                    slots.add(new TimeSchedule(minutesToTime(start), minutesToTime(mtStart)));
                }
                start = timeToMinutes(mt.getEnd());
            }

        }

        if(endWork - start >=meetingDuration){
            slots.add(new TimeSchedule(minutesToTime(start), minutesToTime(endWork)));
        }

        return slots;
    }


    public static List<TimeSchedule> findMeetingSlot(List<TimeSchedule> calendar1, List<TimeSchedule> calendar2, int meetingDuration){
        List<TimeSchedule> slotsAv = new ArrayList<>();

        for(TimeSchedule cal1 : calendar1){
            for(TimeSchedule cal2 : calendar2){
                int start = Math.max(timeToMinutes(cal1.getStart()), timeToMinutes(cal2.getStart()));
                int end = Math.min(timeToMinutes(cal1.getEnd()), timeToMinutes(cal2.getEnd()));
                if(end - start >= meetingDuration){
                    slotsAv.add(new TimeSchedule(minutesToTime(start), minutesToTime(end)));
                }
            }
        }
        return slotsAv;
    }
}
