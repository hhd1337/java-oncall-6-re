package oncall.domain;

import java.util.Arrays;

public enum Holiday {
    SINJUNG(1, 1, "신정"),
    SAM_IL_JUL(3, 1, "삼일절"),
    CHILDREN_DAY(5, 5, "어린이날"),
    HYUN_CHUNG_IL(6, 6, "현충일"),
    GWANG_BOK_JUL(8, 15, "광복절"),
    GAE_CHUN_JUL(10, 3, "개천절"),
    HAN_GEUL_NAL(10, 9, "한글날"),
    CHRISTMAS(12, 25, "크리스마스");

    private int monthInt;
    private int dayOfMonth;
    private String holidayKor;

    Holiday(int monthInt, int dayOfMonth, String holidayKor) {
        this.dayOfMonth = dayOfMonth;
        this.monthInt = monthInt;
        this.holidayKor = holidayKor;
    }

    public static boolean isHoliDay(int monthInt, int dayOfMonth) {
        return Arrays.stream(Holiday.values())
                .anyMatch(holiday -> holiday.monthInt == monthInt
                        && holiday.dayOfMonth == dayOfMonth);
    }
}
