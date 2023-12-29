package com.checkin.utils;

import java.text.SimpleDateFormat;

public class Utils {
    public String formatTime(java.time.LocalTime time) {
        if (time == null) {
            return ""; // Handle null time if needed
        }

        // Format the java.sql.Time as HH:mm
        return new SimpleDateFormat("HH:mm").format(time);
    }
}