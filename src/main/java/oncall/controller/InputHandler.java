package oncall.controller;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import oncall.converter.StringToDayOfWeekConverter;
import oncall.converter.StringToIntConverter;
import oncall.domain.DailyOncall;
import oncall.domain.Holiday;
import oncall.domain.Month;
import oncall.domain.OncallResult;
import oncall.util.DelimiterParser;
import oncall.view.InputView;

public class InputHandler {

    private final InputView inputView;
    private final IteratorInputTemplate inputTemplate;

    public InputHandler(InputView inputView, IteratorInputTemplate iteratorInputTemplate) {
        this.inputView = inputView;
        this.inputTemplate = iteratorInputTemplate;
    }

    public OncallResult inputMonthDay() {
        DelimiterParser parser = new DelimiterParser();
        StringToIntConverter intConverter = new StringToIntConverter();
        StringToDayOfWeekConverter dayOfWeekConverter = new StringToDayOfWeekConverter();
        List<DailyOncall> dailyOncallList = new ArrayList<>();

        return inputTemplate.execute(
                inputView::inputMonthDay,
                value -> {
                    value = value.trim(); // 5,월
                    List<String> parsedFirst = parser.parse(value); // 5랑 월이랑 분리됨.

                    int monthInt = intConverter.convert(parsedFirst.getFirst());  // 5
                    if (monthInt < 1 || monthInt > 12) {
                        throw new IllegalArgumentException("월은 1부터 12사이의 값이어야 합니다. 다시 입력해 주세요.");
                    }
                    int lastDayOfMonth = Month.findBySymbol(monthInt).getLastDayInMonth();

                    DayOfWeek dow = dayOfWeekConverter.convert(parsedFirst.get(1));

                    for (int date = 1; date <= lastDayOfMonth; date++) {
                        dailyOncallList.add(
                                new DailyOncall(monthInt, dow.plus(date - 1), Holiday.isHoliDay(monthInt, date)));
                    }

                    return new OncallResult(dailyOncallList);
                }
        );
    }
}