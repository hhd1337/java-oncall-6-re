package oncall.domain;

import java.time.DayOfWeek;

public class DailyOncall {
    private int dayOfMonth;
    private DayOfWeek dayOfWeek;
    private String crewName;
    private boolean isHoliday;

    public DailyOncall(int dayOfMonth, DayOfWeek dayOfWeek, boolean isHoliday) {
        this.dayOfMonth = dayOfMonth;
        this.dayOfWeek = dayOfWeek;
        this.isHoliday = isHoliday;
    }
}
