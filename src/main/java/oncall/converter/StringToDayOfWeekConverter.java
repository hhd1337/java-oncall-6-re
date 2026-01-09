package oncall.converter;

import java.time.DayOfWeek;
import java.util.List;

public class StringToDayOfWeekConverter implements Converter<String, DayOfWeek> {

    @Override
    public DayOfWeek convert(String source) {
        List<String> dayOfWeekKor = List.of("월", "화", "수", "목", "금", "토", "일");
        if (!dayOfWeekKor.contains(source.trim())) {
            throw new IllegalArgumentException("잘못된 시작 요일 입력입니다.");
        }

        int dayOfWeekValue = dayOfWeekKor.indexOf(source.trim());

        return DayOfWeek.of(dayOfWeekValue + 1);
    }

}
