package cart;

import products.Product;
import discounts.DiscountService;
import discounts.DiscountPolicy;

import java.math.BigDecimal;

public class CartTotalCalculator {
    private final DiscountService discountService;

    public CartTotalCalculator(DiscountService discountService) {
        this.discountService = discountService;
    }

    public BigDecimal calculateTotal(Cart cart, DiscountPolicy discountPolicy) {
        BigDecimal total = BigDecimal.ZERO;

        for (Product product : cart.getProducts()) {
            total = total.add(product.getPrice());
        }

        return discountService.applyDiscount(total, discountPolicy);
    }
}
