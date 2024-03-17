package org.example.finalclock;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Time {
    private LocalTime localTime;
    String time;
    public Time() {
        localTime = LocalTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        time = dtf.format(localTime);
    }
    public Time(boolean alarm) {
        if (alarm) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            localTime = LocalTime.of(0,0,0);
            time = dtf.format(localTime);

        } else {
            localTime = LocalTime.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            time = dtf.format(localTime);
        }
    }

    @Override
    public String toString() {
        return "Time{" +
                "localTime=" + localTime +
                '}';
    }

    public String getCurrentTime () {
        return DateTimeFormatter.ofPattern("HH:mm:ss").format(localTime);
    }
    public void incrementHours (int amount) {
        localTime = localTime.plusHours(amount);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        time = dtf.format(localTime);
    }
    public void incrementMinutes(int amount) {
        localTime = localTime.plusMinutes(amount);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        time = dtf.format(localTime);
    }
    public int getHours() {
        return localTime.getHour();
    }
    public int getMinutes() {
        return localTime.getMinute();
    }
    public int getSeconds() {
        return localTime.getSecond();
    }
}
