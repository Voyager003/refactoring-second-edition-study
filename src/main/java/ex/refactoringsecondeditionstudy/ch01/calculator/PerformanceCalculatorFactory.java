package ex.refactoringsecondeditionstudy.ch01.calculator;

import ex.refactoringsecondeditionstudy.ch01.Performance;
import ex.refactoringsecondeditionstudy.ch01.Play;

public class PerformanceCalculatorFactory {

    public PerformanceCaculator createPerformanceCaculator(Performance performance, Play play) throws Exception {
        return switch (play.getType()) {
            case TRAGEDY -> new TragedyCalculator(performance, play);
            case COMEDY -> new ComedyCalculator(performance, play);
            default -> throw new Exception("알 수 없는 타입입니다.");
        };
    }
}
