package cart;

import customer.Customer;
import products.Product;
import discounts.*;

import java.math.BigDecimal;

public class CartTotalCalculator {
    public BigDecimal calculateTotal(Customer customer) {
        Cart cart = customer.getCart();
        BigDecimal total = BigDecimal.ZERO;

        for (Product product : cart.getProducts()) {
            total = total.add(product.getPrice());
        }

        DiscountPolicy customerDiscount = customer.getDiscountPolicy();
        total = customerDiscount.applyDiscount(total);

        return total;
    }
}
