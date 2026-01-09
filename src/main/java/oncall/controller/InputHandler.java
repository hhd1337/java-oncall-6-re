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

                    int monthInt = intConverter.convert(parsedFirst.get(0));  // 5
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

    public List<String> inputOrderedWeekDayOncallCrews() {
        DelimiterParser parser = new DelimiterParser();
        return inputTemplate.execute(
                inputView::inputOrderedWeekDayOncallCrews,
                value -> {
                    value = value.trim();
                    List<String> parsedCrewNames = parser.parse(value);
                    validateCrewNames(parsedCrewNames);

                    return parsedCrewNames;
                }
        );
    }

    public List<String> inputOrderedWeekEndOncallCrews() {
        DelimiterParser parser = new DelimiterParser();
        return inputTemplate.execute(
                inputView::inputOrderedWeekEndOncallCrews,
                value -> {
                    value = value.trim();
                    List<String> parsedCrewNames = parser.parse(value);
                    validateCrewNames(parsedCrewNames);

                    return parsedCrewNames;
                }
        );
    }

    private void validateCrewNames(List<String> parsedCrewNames) {
        int crewNameListSize = parsedCrewNames.size();

        //- 예외) 닉네임이 중복 입력된 경우
        long distinctNameCount = parsedCrewNames.stream().distinct().count();
        if (distinctNameCount < crewNameListSize) {
            throw new IllegalArgumentException("중복된 닉네임이 존재합니다. 다시 입력해주세요.");
        }
        //- 예외) 5자 초과인 닉네임이 존재할 경우
        parsedCrewNames.forEach(name -> {
            if (name.length() > 5) {
                throw new IllegalArgumentException("5자 초과인 닉네임이 존재합니다. 닉네임은 5자 이하여야 합니다.");
            }
        });
        //- 예외) 닉네임이 5명 미만 혹은 35명 초과일 경우
        if (crewNameListSize < 5 || crewNameListSize > 35) {
            throw new IllegalArgumentException("닉네임이 5명 미만 혹은 35명 초과로 입력되었습니다.");
        }
    }

}