package ex.refactoringsecondeditionstudy.ch03;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Order {
    private int quantity;
    private double itemPrice;

    public double calculateTotalPrice() {
        double basePrice = quantity * itemPrice;
        double discount = 0;
        if (quantity > 100) {
            discount = basePrice * 0.1;
        }
        return basePrice - discount;
    }

    public double calculateTax() {
        double basePrice = quantity * itemPrice;
        return basePrice * 0.1;
    }
}
