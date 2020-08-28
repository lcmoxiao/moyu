package com.jlp.utils;

import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

    public static Date nowTime() {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        return c.getTime();
    }
}
