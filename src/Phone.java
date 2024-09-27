import java.math.BigDecimal;

public class Phone extends Electronics {
    private String color;

    public Phone(String name, BigDecimal price, String category, String description, String powerSource, String color) {
        super(name, price, category, description, powerSource);
        this.color = color;
    }

    // accessors
    public String getColor() {
        return color;
    }

    // mutators
    public void setColor(String color) {
        this.color = color;
    }
}
