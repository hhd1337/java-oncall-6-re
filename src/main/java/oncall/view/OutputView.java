package oncall.view;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;
import oncall.util.ErrorMessage;

public class OutputView {

    public void printErrorMessage(Exception exception) {
        System.out.println(ErrorMessage.PREFIX + exception.getMessage());
    }

    public void printMonthDayInputPrompt() {
        System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
    }

    public void printWeekDayInputPrompt() {
        System.out.print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
    }

    public void printWeekEndInputPrompt() {
        System.out.print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
    }

    public void printDailyOncallResult(int monthInt, int dayOfMonth, DayOfWeek dayOfWeek, String crewName,
                                       boolean isWeekDayAndHoliday) {
        String dayOfWeekKor = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREA);
        if (isWeekDayAndHoliday) {
            System.out.printf("%d월 %d일 %s(휴일) %s%n", monthInt, dayOfMonth, dayOfWeekKor, crewName);
            return;
        }
        System.out.printf("%d월 %d일 %s %s%n", monthInt, dayOfMonth, dayOfWeekKor, crewName);
    }
}
