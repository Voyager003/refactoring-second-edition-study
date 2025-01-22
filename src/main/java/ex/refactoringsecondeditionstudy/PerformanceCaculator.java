package ex.refactoringsecondeditionstudy;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PerformanceCaculator {
    private Performance performance;
    private Play play;

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

}
