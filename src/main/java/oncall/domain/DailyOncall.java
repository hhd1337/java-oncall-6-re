package oncall.domain;

import java.time.DayOfWeek;

public class DailyOncall {
    private int monthInt;
    private DayOfWeek dayOfWeek;
    private String crewName;
    private boolean isHoliday;

    public DailyOncall(int monthInt, DayOfWeek dayOfWeek, boolean isHoliday) {
        this.monthInt = monthInt;
        this.dayOfWeek = dayOfWeek;
        this.isHoliday = isHoliday;
    }

    public boolean isWeekEnd() {
        int dayOfWeekInt = dayOfWeek.getValue();

        return dayOfWeekInt == 6 || dayOfWeekInt == 7;
    }

    public boolean isWeekEndOrHoliday() {
        return isWeekEnd() || isHoliday();
    }

    public boolean isHoliday() {
        return isHoliday;
    }

    public void addCrew(String crewName) {
        this.crewName = crewName;
    }
}
