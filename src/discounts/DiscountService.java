package discounts;

import java.math.BigDecimal;

public class DiscountService {
    public BigDecimal applyDiscount(BigDecimal total, DiscountPolicy discountPolicy) {
        return discountPolicy.applyDiscount(total);
    }
}
