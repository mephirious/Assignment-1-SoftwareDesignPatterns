import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Phone iphoneX = new Phone("IPhone X", new BigDecimal("100.00"), "Electronics", "Black IPhoneX", "Battery", "black");
        System.out.println(iphoneX.getPrice());
        System.out.println(iphoneX.getCategory());
        System.out.println(iphoneX.getDescription());
        System.out.println(iphoneX.getPowerSource());
        System.out.println(iphoneX.getColor());
    }
}