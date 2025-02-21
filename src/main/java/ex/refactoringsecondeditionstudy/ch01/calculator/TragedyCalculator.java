package ex.refactoringsecondeditionstudy.ch01.calculator;

import ex.refactoringsecondeditionstudy.ch01.Performance;
import ex.refactoringsecondeditionstudy.ch01.Play;

public class TragedyCalculator extends PerformanceCaculator {

    public TragedyCalculator(Performance performance, Play play) {
        super(performance, play);
    }

    @Override
    public int amountFor() throws Exception {
        int result = 40000;

        if (performance.getAudience() > 30) {
            result += 1000 * (performance.getAudience() - 30);
        }
        return result;
    }

    @Override
    public int volumeCreditFor() {
        int result = 0;
        result += Math.max(performance.getAudience() - 30, 0);
        return result;
    }
}
