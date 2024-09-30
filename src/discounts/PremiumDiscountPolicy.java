package discounts;

import java.math.BigDecimal;

public class PremiumDiscountPolicy implements DiscountPolicy {
    @Override
    public BigDecimal applyDiscount(BigDecimal total) {
        return total.multiply(new BigDecimal("0.9"));
    }
}
