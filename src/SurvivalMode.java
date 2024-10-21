class SurvivalMode extends GameMode {
    private int playerHealth;
    private int zombieWaves;
    private final int maxWaves = 5;

    protected void initialize() {
        playerHealth = 100;
        zombieWaves = 0;
    }

    protected void handleInput() {
        zombieWaves++;
        playerHealth -= 10;
    }

    protected void updateGameState() {
        System.out.println("Player health: " + playerHealth);
        System.out.println("Current zombie wave: " + zombieWaves + "/" + maxWaves);
    }

    protected void render() {
        System.out.println("Displaying battlefield and zombies...");
    }

    protected boolean isGameOver() {
        return playerHealth <= 0 || zombieWaves >= maxWaves;
    }

    protected void endGame() {
        if (playerHealth > 0) {
            System.out.println("Survival Mode completed. Victory!");
        } else {
            System.out.println("Survival Mode completed. Player lost!");
        }
    }
}
