public class Main {
    public static void main(String[] args) {
        // Example projectile for the PeaShooter
        // Using the Builder Pattern to create a PeaShooter entity
        Plant peaShooter = Plant.Director.constructPeaShooter();

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