// Main class
public class Main {
    public static void main(String[] args) {
        // Example projectile for the PeaShooter
        Projectile peaProjectile = new Projectile(0, 0, 10, 1, 0, 5, 5);

        // Using the Builder Pattern to create a PeaShooter entity
        Entity peaShooter = new Entity.Builder()
                .setName("Pea Shooter")
                .setDescription("A plant that shoots peas")
                .setHealth(300)
                .setDamage(20)
                .setProjectile(peaProjectile)
                .build();


        Game game = Game.getInstance();


        // TODO: GameBoard Generator, ex Sun value, Plants to plant(with planting cooldown), PlantingGrid(where plants are placed and zombies moving), zombiesPre-Spawning area
        game.createGameBoard();

        try {
            game.startGameplay();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Now you can use your peaShooter object
        System.out.println("222Created entity: " + peaShooter.getName());
    }
}