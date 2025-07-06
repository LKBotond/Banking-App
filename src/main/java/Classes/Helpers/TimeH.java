package Classes.Helpers;

import java.time.LocalDate;

public class TimeH {
    public static LocalDate getTimeFromNow(int years) {
        return LocalDate.now().plusYears(years);
    }

    public static LocalDate getCurrentDate(){
        return LocalDate.now();
    }
}
