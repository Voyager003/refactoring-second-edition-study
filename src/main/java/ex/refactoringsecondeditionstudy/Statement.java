package ex.refactoringsecondeditionstudy;

import java.text.NumberFormat;
import java.util.Locale;


public class Statement {
    public String statement(Invoice invoice, Plays plays) throws Exception {
        int totalAmount = 0;
        int volumeCredits = 0;
        StringBuilder result = new StringBuilder(String.format("청구 내역 (고객명: %s)\n", invoice.getCustomer()));
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
        numberFormat.setMaximumFractionDigits(2);

        for (Performance performance : invoice.getPerformances()) {
            Play play = plays.getPlayById(performance.getPlayId());

            int thisAmount = amountFor(performance, play);

            // add volume credits
            volumeCredits += Math.max(performance.getAudience() - 30, 0);

            // add extra credit for every ten comedy attendees
            if (play.getType() == PlayType.COMEDY) {
                volumeCredits += (int) Math.floor((double) performance.getAudience() / 5);
            }

            // print line for this order (앞의 공백 제거)
            result.append(String.format("%s: %s (%d석)\n", play.getName(), numberFormat.format(thisAmount / 100), performance.getAudience()));
            totalAmount += thisAmount;
        }

        result.append(String.format("총액: %s\n", numberFormat.format(totalAmount / 100)));
        result.append(String.format("적립 포인트:: %d점\n", volumeCredits));
        return result.toString();
    }

    private int amountFor(Performance performance, Play play) throws Exception {
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
                throw new Exception(String.format("알 수 없는 장르: %s", play.getType()));
        }
        return result;
    }
}
