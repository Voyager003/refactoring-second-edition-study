package ex.refactoringsecondeditionstudy;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StatementData {
    private Invoice invoice;
    private Plays plays;

    public String getCustomer() {
        return invoice.getCustomer();
    }
}
