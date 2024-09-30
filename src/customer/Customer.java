package customer;

import java.math.BigDecimal;
import discounts.DiscountPolicy;
import cart.Cart;

public class Customer {
    private String name;
    private BigDecimal balance;
    private DiscountPolicy discountPolicy;
    private Cart cart;

    public Customer(String name, BigDecimal balance, DiscountPolicy discountPolicy) {
        this.name = name;
        this.balance = balance;
        this.discountPolicy = discountPolicy;
        this.cart = new Cart();
    }

    // accessors
    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public DiscountPolicy getDiscountPolicy() {
        return discountPolicy;
    }

    public Cart getCart() {
        return cart;
    }

    // mutators
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
}