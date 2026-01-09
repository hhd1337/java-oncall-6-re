package oncall.domain;

import java.util.Arrays;

public enum Month {
    JANUARY(1, 31),
    FEBRUARY(2, 28),
    MARCH(3, 31),
    APRIL(4, 30),
    MAY(5, 31),
    JUNE(6, 30),
    JULY(7, 31),
    AUGUST(8, 31),
    SEPTEMBER(9, 30),
    OCTOBER(10, 31),
    NOVEMBER(11, 30),
    DECEMBER(12, 31);

    private final int symbol;
    private final int lastDayInMonth;

    Month(int symbol, int lastDayInMonth) {
        this.symbol = symbol;
        this.lastDayInMonth = lastDayInMonth;
    }

    public static Month findBySymbol(int symbol) {
        return Arrays.stream(Month.values())
                .filter(month -> month.symbol == symbol)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 형식을 입력하였습니다."));
    }

    public int getSymbol() {
        return this.symbol;
    }

    public int getLastDayInMonth() {
        return lastDayInMonth;
    }
}
