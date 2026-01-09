package oncall.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import oncall.domain.OncallResult;
import oncall.util.ErrorMessage;
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
        AtomicReference<List<String>> weekDayCrewNames = new AtomicReference<>();
        AtomicReference<List<String>> weekEndCrewNames = new AtomicReference<>();

        retryUntilValid(() -> {
            outputView.printWeekDayInputPrompt();
            weekDayCrewNames.set(inputHandler.inputOrderedWeekDayOncallCrews());

            outputView.printWeekEndInputPrompt();
            weekEndCrewNames.set(inputHandler.inputOrderedWeekEndOncallCrews());
        });

        
    }

    private void retryUntilValid(Runnable action) {
        while (true) {
            try {
                action.run();
                return;
            } catch (IllegalArgumentException e) {
                System.out.println(ErrorMessage.PREFIX + e.getMessage());
                // throw new IllegalArgumentException(e.getMessage());
            }
        }
    }
}
