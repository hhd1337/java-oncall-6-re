package oncall.view;

import oncall.util.ErrorMessage;

public class OutputView {

    public void printErrorMessage(Exception exception) {
        System.out.println(ErrorMessage.PREFIX + exception.getMessage());
    }

    public void printMonthDayInputPrompt() {
        System.out.println("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
    }
}
