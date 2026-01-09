package oncall.domain;

import java.util.List;

public class CrewOrders {
    private List<String> weekDayCrewNames;
    private List<String> weekEndCrewNames;

    public CrewOrders(List<String> weekDayCrewNames, List<String> weekEndCrewNames) {
        this.weekDayCrewNames = weekDayCrewNames;
        this.weekEndCrewNames = weekEndCrewNames;
    }

    public List<String> getWeekDayCrewNames() {
        return weekDayCrewNames;
    }

    public List<String> getWeekEndCrewNames() {
        return weekEndCrewNames;
    }
}
