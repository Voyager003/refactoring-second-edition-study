package ex.refactoringsecondeditionstudy.ch01;

import ex.refactoringsecondeditionstudy.ch01.calculator.PerformanceCaculator;
import ex.refactoringsecondeditionstudy.ch01.calculator.PerformanceCalculatorFactory;
import lombok.Getter;

import java.util.List;

@Getter
public class StatementData {
    private Invoice invoice;
    private Plays plays;
    private PerformanceCalculatorFactory performanceCalculatorFactory;

    public StatementData(Invoice invoice, Plays plays) {
        this.invoice = invoice;
        this.plays = plays;
        this.performanceCalculatorFactory = new PerformanceCalculatorFactory();
    }

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
        return performanceCalculatorFactory.createPerformanceCaculator(performance, playFor(performance)).amountFor();
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
        return new PerformanceCaculator(performance, playFor(performance)).volumeCreditFor();
    }
}