package oncall.controller;

import java.util.List;
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

    }

}
