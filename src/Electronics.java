import java.math.BigDecimal;

abstract class Electronics extends Product {
    private String powerSource;

    public Electronics(String name, BigDecimal price, String category, String description, String powerSource) {
        super(name, price, category, description);
        this.powerSource = powerSource;
    }

    // accessors
    public String getPowerSource() {
        return powerSource;
    }

    // mutators
    public void setPowerSource(String powerSource) {
        this.powerSource = powerSource;
    }
}
