package ex.refactoringsecondeditionstudy.ch04;

import java.util.List;

public record ProvinceData(
        String name,
        List<Producer> producers,
        int demand,
        int price
) {
}
