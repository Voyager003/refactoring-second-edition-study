package ex.refactoringsecondeditionstudy.ch03;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Order {
    private int quantity;
    private double itemPrice;

    public double calculateTotalPrice() {
        double basePrice = getBasePrice();
        double discount = (quantity > 100) ? basePrice * 0.1 : 0;
        return basePrice - discount;
    }

    public double calculateTax() {
        return getBasePrice() * 0.1;
    }

    private double getBasePrice() {
        return quantity * itemPrice;
    }
}
