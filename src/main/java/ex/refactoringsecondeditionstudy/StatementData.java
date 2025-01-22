package ex.refactoringsecondeditionstudy;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class StatementData {
    private Invoice invoice;
    private Plays plays;

    public String getCustomer() {
        return invoice.getCustomer();
    }

    public List<Performance> getPerformances() {
        return invoice.getPerformances();
    }

    public Play playFor(Performance performance) {
        return plays.getPlayById(performance.getPlayId());
    }

    public int amountFor(Performance performance) throws Exception {
        return new PerformanceCaculator(performance, playFor(performance)).amountFor();
    }

    public int totalAmount() throws Exception {
        int totalAmount = 0;
        for (Performance performance : invoice.getPerformances()) {
            totalAmount += amountFor(performance);
        }
        return totalAmount / 100;
    }

    public int totalVolumeCredits() {
        int volumeCredit = 0;
        for (Performance performance : invoice.getPerformances()) {
            volumeCredit += volumeCreditFor(performance);
        }
        return volumeCredit;
    }

    private int volumeCreditFor(Performance performance) {
        int result = 0;

        result += Math.max(performance.getAudience() - 30, 0);

        if (playFor(performance).getType().equals(PlayType.COMEDY)) {
            result += Math.floor(performance.getAudience() / 5);
        }

        return result;
    }
}