package com.gamechange.gamechangeapp.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class DateFormatUtil {


    public static String formateDateFromstring(String inputFormat, String outputFormat, String inputDate){

        Date parsed = null;
        String outputDate = "";

        SimpleDateFormat df_input = new SimpleDateFormat(inputFormat, Locale.getDefault());
        SimpleDateFormat df_output = new SimpleDateFormat(outputFormat, Locale.getDefault());

        try {
            parsed = df_input.parse(inputDate);
            outputDate = df_output.format(parsed);

        } catch (ParseException e) {

            e.printStackTrace();
            Log.d("DateFormateUtil", "ParseException - dateFormat");
        }

        return outputDate;

    }


    public static int getDateDiffInDays(String inputFormat, String firstDate, String secondDate)
    {

        int dayDiff=0;
        SimpleDateFormat myFormat = new SimpleDateFormat(inputFormat);
//        String inputString1 = "23 01 1997";
//        String inputString2 = "27 04 1997";

        try {
            Date date1 = myFormat.parse(firstDate);
            Date date2 = myFormat.parse(secondDate);
            long diff = date2.getTime() - date1.getTime();

            dayDiff = (int)TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

            System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dayDiff;

    }

    public static int getDateDiff(Date date1, Date date2)
    {

        int dayDiff=0;

        try {

            long diff = date2.getTime() - date1.getTime();
            dayDiff = (int)TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

            System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dayDiff;

    }

    public static String convertDateToString(Date date, String format)
    {

        String dateStr="";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {

            dateStr = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dateStr;

    }

    public static Date convertStringToDate(String dateStr)
    {
        SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = null;
        try {
            if(dateStr !=null) {
                date1 = myFormat.parse(dateStr);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }



    public static String getLastDateOfMonth(String strDate)
    {
        String lastDate= "";
        Date date=null;
        try {
            date = new SimpleDateFormat("yyyy-MMM-dd", Locale.ENGLISH).parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");
        lastDate = sdf.format(c.getTime());
        System.out.println(sdf.format(c.getTime()));
        return lastDate;
    }

}
