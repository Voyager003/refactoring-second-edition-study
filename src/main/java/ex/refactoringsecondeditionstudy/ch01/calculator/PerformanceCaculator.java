package ex.refactoringsecondeditionstudy.ch01.calculator;

import ex.refactoringsecondeditionstudy.ch01.Performance;
import ex.refactoringsecondeditionstudy.ch01.Play;
import ex.refactoringsecondeditionstudy.ch01.PlayType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PerformanceCaculator {
    protected Performance performance;
    protected Play play;

    public int amountFor() throws Exception {
        int result;
        switch (play.getType()) {
            case TRAGEDY:
                result = 40000;
                if (performance.getAudience() > 30) {
                    result += 1000 * (performance.getAudience() - 30);
                }
                break;
            case COMEDY:
                result = 30000;
                if (performance.getAudience() > 20) {
                    result += 10000 + 500 * (performance.getAudience() - 20);
                }
                result += 300 * performance.getAudience();
                break;
            default:
                throw new Exception("알 수 없는 장르");
        }
        return result;
    }

    public int volumeCreditFor() {
        int result = 0;

        result += Math.max(performance.getAudience() - 30, 0);

        if (play.getType().equals(PlayType.COMEDY)) {
            result += Math.floor(performance.getAudience() / 5);
        }
        return result;
    }
}
