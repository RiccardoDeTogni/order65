/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Date;
import java.util.Calendar;
import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;
/**
 *
 * @author Riccardo
 */
public class DateUtils {
    
    public static int getAge(Date first, Date last) {
    Calendar a = getCalendar(first);
    Calendar b = getCalendar(last);
    int diff = b.get(YEAR) - a.get(YEAR);
    if (a.get(MONTH) > b.get(MONTH) || 
        (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
        diff--;
    }
    return diff;
}

public static Calendar getCalendar(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal;
}
    
}
