public class Main {
    public static void main(String[] args) {
        Game game = Game.getInstance();

        // TODO: GameBoard Generator, ex Sun value, Plants to plant(with planting cooldown), PlantingGrid(where plants are placed and zombies moving), zombiesPre-Spawning area
        game.createGameSession();

        try {
            game.startGameplay();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
