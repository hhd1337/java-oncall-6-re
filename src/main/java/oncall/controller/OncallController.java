package oncall.controller;

import java.time.DayOfWeek;
import java.util.List;
import oncall.domain.DailyOncall;
import oncall.domain.OncallResult;
import oncall.view.OutputView;

public class OncallController {

    private final InputHandler inputHandler;
    private final OutputView outputView;

    public OncallController(InputHandler inputHandler, OutputView outputView) {
        this.inputHandler = inputHandler;
        this.outputView = outputView;
    }

    public void process() {
        outputView.printMonthDayInputPrompt();
        OncallResult oncallResult = inputHandler.inputMonthDay();

        outputView.printWeekDayInputPrompt();
        List<String> weekDayCrewNames = inputHandler.inputOrderedWeekDayOncallCrews();

        outputView.printWeekEndInputPrompt();
        List<String> weekEndCrewNames = inputHandler.inputOrderedWeekEndOncallCrews();

        oncallResult.putCrewsInDailyOncallList(weekDayCrewNames, weekEndCrewNames);

        printOncallResult(oncallResult);
    }

    private void printOncallResult(OncallResult oncallResult) {
        for (DailyOncall dailyOncall : oncallResult.getDailyOncallList()) {
            int MonthInt = dailyOncall.getMonthInt();
            int DayOfMonth = dailyOncall.getDayOfMonth();
            DayOfWeek DayOfWeek = dailyOncall.getDayOfWeek();
            String crewName = dailyOncall.getCrewName();
            boolean isWeekDayAndHoliday = dailyOncall.isWeekDayAndHoliday();

            outputView.printDailyOncallResult(MonthInt, DayOfMonth, DayOfWeek, crewName, isWeekDayAndHoliday);
        }
    }
}
