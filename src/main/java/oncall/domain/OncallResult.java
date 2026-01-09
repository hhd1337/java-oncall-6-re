package oncall.domain;

import java.util.List;

public class OncallResult {
    private List<DailyOncall> dailyOncallList;

    public OncallResult(List<DailyOncall> dailyOncallList) {
        this.dailyOncallList = dailyOncallList;
    }

    public void addDailyOncall(DailyOncall dailyOncall) {
        dailyOncallList.add(dailyOncall);
    }

    // public List<DailyOncall> putCrewsInDailyOncallList(){
    // }
}
