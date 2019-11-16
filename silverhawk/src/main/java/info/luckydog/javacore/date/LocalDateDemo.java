package info.luckydog.javacore.date;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * LocalDateDemo
 *
 * @author eric
 * @since 2019/9/5
 */
public class LocalDateDemo {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Test
    public void localDateDemo() {
        LocalDate now = LocalDate.now();
        System.out.println(getMonthAfter(now, 1));
        System.out.println(getMonthBefore(now, 1));
        System.out.println(getMonthFirstDate(now));
        System.out.println(getMonthLastDate(now));
        System.out.println(getLocalDateStr(now, ""));
    }


    /**
     * 获取 month 个月后的日期
     *
     * @param localDate
     * @param month
     * @return
     */
    public static LocalDate getMonthAfter(LocalDate localDate, int month) {
        return localDate.plusMonths(month);
    }

    /**
     * 获取 month 个月前的日期
     *
     * @param localDate
     * @param month
     * @return
     */
    public static LocalDate getMonthBefore(LocalDate localDate, int month) {
        return localDate.minusMonths(month);
    }

    /**
     * 获取当月第一天日期
     *
     * @param localDate
     * @return
     */
    public static LocalDate getMonthFirstDate(LocalDate localDate) {
        return LocalDate.of(localDate.getYear(), localDate.getMonth(), 1);
    }

    /**
     * 获取当月第一天日期
     *
     * @param localDate
     * @return
     */
    public static LocalDate getMonthLastDate(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 日期转字符串
     *
     * @param localDate
     * @param format
     * @return
     */
    public static String getLocalDateStr(LocalDate localDate, String format) {
        if (StringUtils.isBlank(format)) {
            format = DATE_FORMAT;
        }
        return localDate.format(DateTimeFormatter.ofPattern(format));
    }
}
