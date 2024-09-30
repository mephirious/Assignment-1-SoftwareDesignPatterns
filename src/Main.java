import customer.Customer;
import discounts.NoDiscountPolicy;
import discounts.PremiumDiscountPolicy;
import products.Phone;
import products.Product;
import cart.*;
import discounts.DiscountPolicy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Создаем продукты
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

        // Создаем клиентов
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

        CartTotalCalculator calculator = new CartTotalCalculator();

        System.out.println(calculator.calculateTotal(nurislam));
        nurislam.getCart().addProduct(book);
        nurislam.getCart().addProduct(iphoneX);
        nurislam.getCart().addProduct(shirt);
        nurislam.getCart().addProduct(book);
        System.out.println(calculator.calculateTotal(nurislam));

        System.out.println(calculator.calculateTotal(adai));
        adai.getCart().addProduct(shirt);
        adai.getCart().addProduct(book);
        System.out.println(calculator.calculateTotal(adai));
    }
}
