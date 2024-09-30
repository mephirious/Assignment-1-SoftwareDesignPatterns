package discounts;

import java.math.BigDecimal;

public class NoDiscountPolicy implements DiscountPolicy {
    @Override
    public BigDecimal applyDiscount(BigDecimal total) {
        return total;
    }
}