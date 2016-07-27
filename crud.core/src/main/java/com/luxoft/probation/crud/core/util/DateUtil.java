package com.luxoft.probation.crud.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Utility class for working with Date & Time
 * <p>
 * Created by HHayryan on 5/24/2016.
 */
public final class DateUtil {

    public static final String DEFAULT_DATE_TIME_PATTERN = "dd-MM-yyyy HH:mm";

    private static final int MIN_FLIGHT_GAP_IN_MINUTES = 40;

    private static SimpleDateFormat dateFormat;

    private static Calendar calendar;

    static {
        dateFormat = new SimpleDateFormat();
        calendar = Calendar.getInstance(Locale.getDefault());
    }

    private DateUtil() {
    }

    /**
     * Check if provided arrival and departure dates diff more than 40 minutes then return TRUE
     *
     * @param arrivalDate   - Date of flight arrival
     * @param departureDate - departure Date of next flight
     * @return boolean - true and only true if arrival and departure dates gap more than 40 minutes
     */
    public static boolean isAcceptableFlightTimeGap(final Date arrivalDate, final Date departureDate) {
        long diff = departureDate.getTime() - arrivalDate.getTime();

        return TimeUnit.MILLISECONDS.toMinutes(diff) >= MIN_FLIGHT_GAP_IN_MINUTES;
    }

    /**
     * Format string value to date
     *
     * @param format - date pattern
     * @param date   - date string value
     * @return java.util.Date - parsed Date object instance
     */
    public static Date getDateFromString(final String format, final String date) throws ParseException {
        dateFormat.applyPattern(format);
        return dateFormat.parse(date);
    }

    /**
     * Format string value to date using default Date Time pattern dd-MM-yyyy HH:mm
     *
     * @param date - String value of date in dd-MM-yyyy HH:mm pattern
     * @return Date - parsed Date object instance
     */
    public static Date getDateFromString(final String date) throws ParseException {
        return getDateFromString(DEFAULT_DATE_TIME_PATTERN, date);
    }

    public static Date getIntervalDay(final Date date, final int interval) {
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + (interval * 24));

        return calendar.getTime();
    }
}
