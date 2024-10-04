// Main class
public class Main {
    public static void main(String[] args) {
        // Example projectile for the PeaShooter
        Projectile peaProjectile = new Projectile(0, 0, 1, 0, 5, 5);

        // Using the Builder Pattern to create a PeaShooter entity
        Plant peaShooter = (new Plant.Builder()
                .setName("Pea Shooter")
                .setDescription("A plant that shoots peas")
                .setHealth(300)
                .setDamage(20)
                .setProjectile(peaProjectile)
                .build());

        Game game = Game.getInstance();

        // TODO: GameBoard Generator, ex Sun value, Plants to plant(with planting cooldown), PlantingGrid(where plants are placed and zombies moving), zombiesPre-Spawning area
        game.createGameSession();

        try {
            game.startGameplay();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Now you can use your peaShooter object
        System.out.println("Created entity: " + peaShooter.getName());
    }
}