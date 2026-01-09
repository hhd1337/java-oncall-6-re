package oncall.domain;

import java.util.Collections;
import java.util.List;

public class OncallResult {
    private List<DailyOncall> dailyOncallList;

    public OncallResult(List<DailyOncall> dailyOncallList) {
        this.dailyOncallList = dailyOncallList;
    }

    public List<DailyOncall> putCrewsInDailyOncallList(CrewOrders crewOrders) {
        List<String> weekdayCrews = crewOrders.getWeekDayCrewNames();
        List<String> weekendCrews = crewOrders.getWeekEndCrewNames();

        int weekdayCrewsIndex = 0;
        int weekendCrewsIndex = 0;
        //- 조건) 비상근무자는 어떤 경우에도 연속 2일 근무할 수 없다.
        //- 조건) 순번 상 특정근무자가 연속 2일 근무하는 상황에는 다음 근무자와 순서를 바꾼다.
        String yesterdayCrewName = "";
        for (DailyOncall dailyOncall : dailyOncallList) {
            if (weekdayCrewsIndex >= weekdayCrews.size()) {
                weekdayCrewsIndex = weekdayCrewsIndex % weekdayCrews.size();
            }
            if (weekendCrewsIndex >= weekendCrews.size()) {
                weekendCrewsIndex = weekendCrewsIndex % weekendCrews.size();
            }

            // 주중, 평일이라면
            if (!dailyOncall.isWeekEndOrHoliday()) {
                String todayCrewName = weekdayCrews.get(weekdayCrewsIndex);
                //어제 근무한 사람이 오늘도 근무대상이라면
                if (yesterdayCrewName.equals(todayCrewName)) {
                    Collections.swap(weekdayCrews, weekdayCrewsIndex, weekdayCrewsIndex + 1);
                    todayCrewName = weekdayCrews.get(weekdayCrewsIndex);
                    dailyOncall.addCrew(todayCrewName);
                    weekdayCrewsIndex++;
                    continue;
                }
                dailyOncall.addCrew(todayCrewName);
                weekdayCrewsIndex++;
            }
            // 만약 휴일이나 주말이라면
            if (dailyOncall.isWeekEndOrHoliday()) {
                String todayCrewName = weekendCrews.get(weekendCrewsIndex);
                if (yesterdayCrewName.equals(todayCrewName)) {
                    Collections.swap(weekendCrews, weekendCrewsIndex, weekendCrewsIndex + 1);
                    todayCrewName = weekendCrews.get(weekendCrewsIndex);
                    dailyOncall.addCrew(todayCrewName);
                    weekendCrewsIndex++;
                    continue;
                }
                dailyOncall.addCrew(todayCrewName);
                weekendCrewsIndex++;
            }
        }

        return dailyOncallList;
    }

    public List<DailyOncall> getDailyOncallList() {
        return dailyOncallList;
    }
}
