package products;

import java.math.BigDecimal;

public class Product {
    private String name;
    private BigDecimal price;
    private String category;
    private String description;

    public Product(String name, BigDecimal price, String category, String description)
    {
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
    }

    // accessors
    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    // mutators
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

