/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package las.common_classes;

/**
 *
 * @author Uer
 */

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateChecker {
    
    public static boolean isValid(String permitissueDate){
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        LocalDate localDate = LocalDate.parse(permitissueDate, formatter);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return getDateAfter3months(date);
        
        
        }
    
    
       public static boolean getDateAfter3months(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 3);
        
        return isafter3months(c.getTime());
    }
    
    public static boolean isafter3months(Date checkdate){
        Calendar cal = Calendar.getInstance();
        Calendar calcheck = Calendar.getInstance();
        calcheck.setTime(checkdate);

        if (cal == null || calcheck == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
  
        if (cal.get(Calendar.YEAR) > calcheck.get(Calendar.YEAR)) return true;
        if (cal.get(Calendar.YEAR) < calcheck.get(Calendar.YEAR)) return false;
        return cal.get(Calendar.DAY_OF_YEAR) >= calcheck.get(Calendar.DAY_OF_YEAR);
    
    
    
    }
    
    
    
}
