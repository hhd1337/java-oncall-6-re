package oncall.view.formatter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class LocalDateTimeFormatter {
    public static String localDateTimeToStringFormat(LocalDate date) {
        String dow = LocalDateTimeToDayofWeekKorShort(date);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "MM월 dd일 " + dow,
                Locale.KOREAN
        );
        return date.format(formatter);
    }

    public static String LocalDateTimeToDayofWeekKorShort(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN);
    }
}
