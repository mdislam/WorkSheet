package com.mesba.worksheet;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Helpers {
    public static String getCurrentDate(Date today) {
        if(today == null) today = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(today);
    }

    public static String getCurrentTime(Date today) {
        if(today == null) today = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.TIME_FORMAT);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(today);
    }
}
