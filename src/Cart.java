import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> products = new ArrayList<Product>();

    public void addProduct(Product product) {
        if (product != null) {
            products.add(product);
        }
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    // accessors
    public List<Product> getProducts() {
        return this.products;
    }
}

class CartTotalCalculator {
    public BigDecimal calculateTotal(Cart cart) {
        BigDecimal total = BigDecimal.ZERO;
        for (Product product : cart.getProducts()) {
            total = total.add(product.getPrice());
        }

        return total;
    }
}
