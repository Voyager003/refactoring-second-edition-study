package ex.refactoringsecondeditionstudy;



public class Statement {
    public String statement(Invoice invoice, Plays plays) throws Exception {
        int totalAmount = 0;
        int volumeCredits = 0;
        StringBuilder result = new StringBuilder(String.format("청구 내역 (고객명: %s)\n", invoice.getCustomer()));

        for (Performance performance : invoice.getPerformances()) {
            volumeCredits = volumeCreditFor(plays, performance, volumeCredits);

            // print line for this order (앞의 공백 제거)
            result.append(String.format("%s: $%d %d석\n",playFor(plays, performance).getName(), amountFor(performance, plays) / 100, performance.getAudience()));
            totalAmount += amountFor(performance, plays);
        }

        result.append(String.format("총액: %s\n", totalAmount / 100));
        result.append(String.format("적립 포인트: %d점\n", volumeCredits));
        return result.toString();
    }

    private int volumeCreditFor(Plays plays, Performance performance, int volumeCredits) {
        // add volume credits
        volumeCredits += Math.max(performance.getAudience() - 30, 0);

        // add extra credit for every ten comedy attendees
        if (playFor(plays, performance).getType() == PlayType.COMEDY) {
            volumeCredits += Math.floor(performance.getAudience() / 5);
        }
        return volumeCredits;
    }

    private int amountFor(Performance performance, Plays plays) throws Exception {
        int result;
        switch (playFor(plays, performance).getType()) {
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
                throw new Exception(String.format("알 수 없는 장르: %s"));
        }
        return result;
    }

    private Play playFor(Plays plays, Performance performance) {
        return plays.getPlayById(performance.getPlayId());
    }
}
