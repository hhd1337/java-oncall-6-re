package oncall.domain;

import java.time.DayOfWeek;

public class DailyOncall {
    private int monthInt;
    private int dayOfMonth;
    private DayOfWeek dayOfWeek;
    private String crewName;
    private boolean isHoliday;

    public DailyOncall(int monthInt, int dayOfMonth, DayOfWeek dayOfWeek, boolean isHoliday) {
        this.monthInt = monthInt;
        this.dayOfMonth = dayOfMonth;
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

    public boolean isWeekDayAndHoliday() {
        return !isWeekEnd() && isHoliday();
    }

    public boolean isHoliday() {
        return isHoliday;
    }

    public void addCrew(String crewName) {
        this.crewName = crewName;
    }

    public int getMonthInt() {
        return monthInt;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public String getCrewName() {
        return crewName;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }
}
