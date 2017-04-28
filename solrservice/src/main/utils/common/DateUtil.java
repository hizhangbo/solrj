package main.utils.common;


import org.apache.solr.common.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhangbo on 2017/4/10.
 */
public class DateUtil {
    private static String defaultDatePattern = "yyyy-MM-dd ";

    public static String getDatePattern()
    {
        return defaultDatePattern;
    }

    public static String getToday()
    {
        Date today = new Date();
        return format(today);
    }

    public static String format(Date date)
    {
        return date == null ? " " : format(date, getDatePattern());
    }

    public static String format(Date date, String pattern)
    {
        return date == null ? " " : new SimpleDateFormat(pattern).format(date);
    }

    public static Date parse(String strDate) throws ParseException
    {
        return StringUtils.isEmpty(strDate) ? null : parse(strDate,
                getDatePattern());
    }

    public static Date parse(String strDate, String pattern)
            throws ParseException
    {
        return StringUtils.isEmpty(strDate) ? null : new SimpleDateFormat(
                pattern).parse(strDate);
    }

    public static Date addMonth(Date date, int n)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }

    public static String getLastDayOfMonth(String year, String month)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(year));
        // cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);
        return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
    }

    public static Date getDate(String year, String month, String day)
            throws ParseException
    {
        String result = year + "- "
                + (month.length() == 1 ? ("0 " + month) : month) + "- "
                + (day.length() == 1 ? ("0 " + day) : day);
        return parse(result);
    }
}
