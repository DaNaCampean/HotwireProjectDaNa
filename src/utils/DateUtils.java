package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static LocalDate returnCurrentDate(){
        return LocalDate.now(); // Set the current date
    }

    public static String formatDates(LocalDate date, String formatPattern){
        // format dates to be able to create the list for comparison
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);
        return date.format(formatter);
    }
}
