import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Random rand = new Random();
        Mage ilya = new Mage("Exoneges", 20, rand.nextInt(40), rand.nextInt(6)+3, 100);
        Warrior nursultan = new Warrior("mephirious", 30, rand.nextInt(70), rand.nextInt(4)+1, 3);

        Battle battle = new Battle(nursultan, ilya);
        battle.printResults();
        battle.start();
        battle.printResults();
    }
}
