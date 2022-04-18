package com.ercan.utils;


import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

    private TimeUtils(){throw new IllegalAccessError("TimeUtils");}


    public static class DATEFORMAT {

        private DATEFORMAT(){throw new IllegalAccessError("TimeUtils.DATEFORMAT");}

        public static final String YYYY_MM_DD = "yyyy-MM-dd";
        public static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH";
        public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
        public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

        public static final String DD_MM_YYYY = "dd-MM-yyyy";
        public static final String DD_MM_YYYY_HH = "dd-MM-yyyy HH";
        public static final String DD_MM_YYYY_HH_MM = "dd-MM-yyyy HH:mm";
        public static final String DD_MM_YYYY_HH_MM_SS = "dd-MM-yyyy HH:mm:ss";

        /* source : DateFormatUtils.class */
        public static final String ISO_8601_EXTENDED_TIME_FORMAT = "HH:mm:ss";
        public static final String SMTP_DATETIME_FORMAT = "EEE, dd MMM yyyy HH:mm:ss Z";
        public static final String ISO_8601_EXTENDED_TIME_TIME_ZONE_FORMAT = "HH:mm:ssZZ";
        public static final String ISO_8601_EXTENDED_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
        public static final String ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZZ";
    }

    /* get 24 hour ago time */
    public static Long get24HoursAgoTime(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, -24);
        return cal.getTimeInMillis();
    }

}
