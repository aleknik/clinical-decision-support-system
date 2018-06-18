package com.aleknik.cdss.cdssservice.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {

    public static Date Days(Date date, int days) {
        LocalDate ldate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate newDate = ldate.minusDays(days);

        return Date.from(newDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date Months(Date date, int months) {
        LocalDate ldate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate newDate = ldate.minusMonths(months);

        return Date.from(newDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
