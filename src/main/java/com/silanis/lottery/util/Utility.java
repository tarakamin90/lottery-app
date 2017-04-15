package com.silanis.lottery.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by TARAK on 2017-04-13.
 */
public class Utility {
    private static SimpleDateFormat sdf = new SimpleDateFormat("MMMyyyy");

    public static String getMonthYearSeriesNo() {
        // Every month new ticket series would be assigned to all tickets
        // and that would be combination of current month and year eg: JAN2016
        return sdf.format(new Date()).toUpperCase();
    }

    public static String getUserReadableMonthYearSeries() {
        SimpleDateFormat sdf = new SimpleDateFormat(("MMM-yyyy"));
        return sdf.format(new Date()).toUpperCase();
    }

    public static String getUserReadableMonthYearSeries(String seriesNo) {
        String month = seriesNo.substring(0 , 3);
        String year = seriesNo.substring(3, seriesNo.length());

        return month + " - " + year;
    }
}
