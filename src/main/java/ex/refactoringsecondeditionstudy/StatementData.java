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
}