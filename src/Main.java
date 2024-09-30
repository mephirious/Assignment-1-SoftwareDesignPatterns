import customer.Customer;
import discounts.NoDiscountPolicy;
import discounts.PremiumDiscountPolicy;
import products.Phone;
import products.Product;
import cart.CartTotalCalculator;
import discounts.DiscountService;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Phone iphoneX = new Phone(
                "IPhone X",
                new BigDecimal("100.00"),
                "electronics",
                "Black IPhoneX",
                "Battery",
                "black"
        );

        Product book = new Product(
                "Refactoring",
                new BigDecimal("11.00"),
                "books",
                "book about programming..."
        );

        Product shirt = new Product(
                "White shirt XL",
                new BigDecimal("20.00"),
                "clothes",
                "XL white shirt"
        );

        Customer nurislam = new Customer(
                "Nurislam",
                new BigDecimal("1000.00"),
                new NoDiscountPolicy()
        );

        Customer adai = new Customer(
                "Adai",
                new BigDecimal("29.20"),
                new PremiumDiscountPolicy()
        );

        DiscountService discountService = new DiscountService();
        CartTotalCalculator calculator = new CartTotalCalculator(discountService);

        nurislam.getCart().addProduct(book);
        nurislam.getCart().addProduct(iphoneX);
        nurislam.getCart().addProduct(shirt);

        adai.getCart().addProduct(shirt);
        adai.getCart().addProduct(book);

        System.out.println("Total for Nurislam: " + calculator.calculateTotal(nurislam.getCart(), nurislam.getDiscountPolicy()));
        System.out.println("Total for Adai: " + calculator.calculateTotal(adai.getCart(), adai.getDiscountPolicy()));
    }
}
