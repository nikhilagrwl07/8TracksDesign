//package com.Util;
//
//
//
//import javax.ws.rs.core.Response;
//import java.sql.Timestamp;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.TimeZone;
//
///**
// * Created by sanjay.rajput on 17/11/15.
// */
//
//public class TimeService {
//
//    public static Timestamp now() {
//        return new Timestamp(new Date().getTime());
//    }
//
//    public static Long currentEpoch() {
//        return new Date().getTime();
//    }
//
//    public static Timestamp getTimestamp(Date date) {
//        return new Timestamp(date.getTime());
//    }
//
//    public static Date currentDate() throws Exception {
//        DateFormat df1 = new SimpleDateFormat("yyyy/MM/dd");
//        try {
//            return df1.parse(df1.format(new Date()));
//        } catch (ParseException e) {
//            throw new Exception(e.getMessage());
//        }
//    }
//
////    public static Timestamp epochToTimestamp(long epoc) {
////        return new Timestamp(new Date(epoc).getTime());
////    }
//
//    public static Timestamp epochToTimestamp(long epoc) {
//        return new Timestamp(epoc);
//    }
//
//    public static Date epochToDate(long epoc) {
//        return new Date(epoc);
//    }
//
//    public static java.sql.Date epochToSqlDate(long epoc) {
//        return new java.sql.Date(epoc);
//    }
//
//    public static Long getEpoch(Date date) {
//        return date.getTime();
//    }
//
//    //----- Parse time string in Format (D HH:mm:ss) D is day count from today ------
//    public static Timestamp parseTimerString(String time) throws Exception {
//        DateFormat df2 = new SimpleDateFormat("HH:mm:ss");
//        try {
//            int dayCount = Integer.parseInt(time.substring(0, time.indexOf(' ')));
//            Date date = df2.parse(time.substring(time.indexOf(' ') + 1));
//            Calendar c = Calendar.getInstance();
//            c.setTime(date);
//            Calendar c2 = Calendar.getInstance();
//            c2.setTime(new Date());
//            c2.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY));
//            c2.set(Calendar.MINUTE, c.get(Calendar.MINUTE));
//            c2.set(Calendar.SECOND, c.get(Calendar.SECOND));
//            c2.add(Calendar.DATE, dayCount);
//            return getTimestamp(c2.getTime());
//        } catch (ParseException e) {
//            throw new Exception(e.getMessage());
//        }
//    }
//
//    public static boolean isTimeAfterCurrent(long time) {
//
//        Date laterTime = new Date(time);
//        Date currentTime = new Date();
//
//        if(laterTime.after(currentTime)) {
//            return true;
//        }
//
//        return false;
//    }
//
//    public static boolean isTimeBeforeCurrent(long timeInSecs) {
//
//        Date laterTime = new Date(timeInSecs);
//        Date currentTime = new Date();
//
//        if(laterTime.before(currentTime)) {
//            return true;
//        }
//
//        return false;
//    }
//
//    public static boolean isTimeBefore(Date date, int days, int hours, int minutes, int seconds) {
//
//        Calendar c = Calendar.getInstance();
//        c.setTime(new Date());
//        c.add(Calendar.DAY_OF_MONTH, -days);
//        c.add(Calendar.HOUR_OF_DAY, -hours);
//        c.add(Calendar.MINUTE, -minutes);
//        c.add(Calendar.SECOND, -seconds);
//
//        Date laterTime = c.getTime();
//
//        if(date.before(laterTime)) {
//            return true;
//        }
//
//        return false;
//    }
//
//    public static Date getCustomDate(Date date, int days, int hours, int minutes, int seconds) {
//        Calendar c = Calendar.getInstance();
//        c.setTime(date);
//        c.add(Calendar.DAY_OF_MONTH, days);
//        c.add(Calendar.HOUR_OF_DAY, hours);
//        c.add(Calendar.MINUTE, minutes);
//        c.add(Calendar.SECOND, seconds);
//        return c.getTime();
//    }
//
//    public static Date getTodayTime(int hour, int minute, int second) {
//        Calendar c = Calendar.getInstance();
//        c.setTime(new Date());
//        c.set(Calendar.HOUR_OF_DAY, hour);
//        c.set(Calendar.MINUTE, minute);
//        c.set(Calendar.SECOND, second);
//        return c.getTime();
//    }
//
//    public boolean isTimeBetween(Date date, Date from, Date to) {
//        if (date.compareTo(from) >= 0 && date.compareTo(to) < 0) {
//            return true;
//        }
//
//        return false;
//    }
//
//
//    public String getDateFormated3(Date date) throws JarvisException {
//        DateFormat df3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//        return df3.format(date);
//    }
//
//    public String getDateFormated3(Timestamp timestamp) throws JarvisException {
//        return getDateFormated3(new Date(timestamp.getTime()));
//    }
//
//    public String getDateFormated4(Date date) throws JarvisException {
//        DateFormat df4 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//        return df4.format(date);
//    }
//
//    public Date getDateFromFormat4(String date) throws JarvisException {
//        DateFormat df4 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//        try {
//            TimeZone tz = TimeZone.getTimeZone("UTC");
//            df4.setTimeZone( tz );
//            return df4.parse(date);
//        } catch (ParseException e) {
//            throw new JarvisException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to parse date - " + e.getMessage());
//        }
//    }
//
//    public static Date getDateFromBigfootFormat(String date) throws JarvisException {
//        DateFormat df4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//            return df4.parse(date);
//        } catch (ParseException e) {
//            throw new JarvisException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Failed to parse date" + e.getMessage());
//        }
//    }
//
//    public static String formatDateInBigfootFormat(Date date) {
//        DateFormat df4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        return df4.format(date);
//    }
//}
