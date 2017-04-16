package com.silanis.lottery.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by TARAK on 2017-04-13.
 */
public class Utility {
    private static final Log logger = LogFactory.getLog(Utility.class);
    private static SimpleDateFormat sdf = new SimpleDateFormat("MMMyyyy");

    /**
     * // Every month new ticket series would be assigned to all tickets
     // and that would be combination of current month and year eg: JAN2016

     * @return Series
     */
    public static String getMonthYearSeriesNo() {


        return sdf.format(new Date()).toUpperCase();
    }

    public static String getUserReadableMonthYearSeries() {
        SimpleDateFormat sdf = new SimpleDateFormat(("MMM-yyyy"));
        return sdf.format(new Date()).toUpperCase();
    }

    public static String getUserReadableMonthYearSeries(String seriesNo) {
        logger.debug("Start getMonthYearSeriesNo()");
        String month = seriesNo.substring(0 , 3);
        String year = seriesNo.substring(3, seriesNo.length());
        logger.debug("Month:" +month + " ," + "Year: " + year);

        logger.debug("End getMonthYearSeriesNo()");
        return month + " - " + year;
    }
}
