package ex.refactoringsecondeditionstudy;


public class Statement {
    public String statement(Invoice invoice, Plays plays) throws Exception {
        StatementData statementData = new StatementData(invoice, plays);
        return renderPlainText(statementData);
    }

    private String renderPlainText(StatementData statementData) throws Exception {
        StringBuilder result = new StringBuilder(String.format("청구내역 (고객명: %s)\n", statementData.getCustomer()));
        for (Performance performance : statementData.getPerformances()) {
            result.append(String.format("%s: $%d %d석\n", statementData.playFor(performance).getName(), statementData.amountFor(performance) / 100, performance.getAudience()));
        }

        result.append(String.format("총액: $%d\n", statementData.totalAmount()));
        result.append(String.format("적립 포인트: %d점", statementData.totalVolumeCredits()));
        return result.toString();
    }

    private String renderHtml(StatementData statementData) throws Exception {
        StringBuilder result = new StringBuilder(String.format("<h1> 청구내역 (고객명: %s)\n </h1>", statementData.getCustomer()));
        result.append("<table> \n");
        result.append("<tr><th> 연극 </th> <th>좌석 수</th> <th>금액</th>");
        for (Performance performance : statementData.getPerformances()) {
            result.append(String.format("<tr><td> %s: </td> <td> $%d </td> <td> %d석 </td></tr>\n",statementData.playFor(performance).getName(), statementData.amountFor(performance) / 100, performance.getAudience()));
        }
        result.append("</table>\n");

        result.append(String.format("총액: $%d\n", statementData.totalAmount()));
        result.append(String.format("적립 포인트: %d점", statementData.totalVolumeCredits()));
        return result.toString();
    }

    private int totalAmount(Invoice invoice, Plays plays) throws Exception {
        int totalAmount = 0;
        for (Performance performance : invoice.getPerformances()) {
            totalAmount += amountFor(performance, plays);
        }
        return totalAmount / 100;
    }

    private int volumeCreditFor(Plays plays, Performance performance) {
        int result = 0;
        // add volume credits
        result += Math.max(performance.getAudience() - 30, 0);

        // add extra credit for every ten comedy attendees
        if (playFor(plays, performance).getType() == PlayType.COMEDY) {
            result += Math.floor(performance.getAudience() / 5);
        }
        return result;
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

    private int totalVolumeCredits(Invoice invoice, Plays plays) {
        int volumeCredit = 0;
        for (Performance performance : invoice.getPerformances()) {
            volumeCredit += volumeCreditFor(plays, performance);
        }
        return volumeCredit;
    }

    private Play playFor(Plays plays, Performance performance) {
        return plays.getPlayById(performance.getPlayId());
    }
}
