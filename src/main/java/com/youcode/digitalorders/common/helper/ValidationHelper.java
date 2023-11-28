package com.youcode.digitalorders.common.helper;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;

public class ValidationHelper {
    public static boolean validateEndDateBigThanStartDate(Date startDate, Date endDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.DAY_OF_MONTH, 3);
        Date startDatePlus3Days = cal.getTime();

        return endDate.after(startDatePlus3Days);
    }

    public static boolean validateDatesDefirence(Date startDate, Date endDate, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.DAY_OF_MONTH, days);
        Date startDatePlusDays = cal.getTime();

        return endDate.after(startDatePlusDays);
    }

    private Boolean DateMustBeToday(LocalDate date) {
        try {
            LocalDate currentDate = LocalDate.now();

            if (!date.isEqual(currentDate)) {
                return false;
            }
        } catch (DateTimeParseException e) {
                return false;
        }

            return true;
    }
}
