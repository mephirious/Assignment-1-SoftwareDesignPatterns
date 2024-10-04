interface IGame {
    void createGameSession();
    void startGameplay();
}

public class Game {
    private static Game instance;
    private GameBoard gameBoard;
    private int level;
    private int tickNumber;

    // Tick every 50 milliseconds (20 FPS)
    private static final int TICK_RATE_MS = 50;
    // Can use this to stop the loop externally
    private volatile boolean isRunning = true;

    public Game() {
        level = 0;
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void createGameSession() {
        this.gameBoard = new GameBoard();
        this.tickNumber = 0;
    }

    public void startGameplay() throws InterruptedException {
        long lastTime = System.currentTimeMillis();

        while (isRunning) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - lastTime;

            if (elapsedTime >= TICK_RATE_MS) {
                // Execute game logic in each tick
                this.tickNumber += 1;
                boolean continueGame = gameBoard.Tick();

                if (tickNumber % 100 == 0) {

                    // Using the Builder Pattern to create a PeaShooter entity
                    this.gameBoard.addEntity(new Plant.Builder()
                            .setName("Pea Shooter" + this.tickNumber)
                            .setDescription("A plant that shoots peas")
                            .setHealth(300)
                            .setDamage(20)
                            .setProjectile(
                                    new Projectile(0, 10, 1, 0, 5, 5)).
                            setAverageActionSpeed(1.425)
                            .build());
                }

                if (!continueGame) {
                    isRunning = false;
                    break;
                }

                // Reset the last tick time
                lastTime = currentTime;
            }

            // Calculate sleep time to avoid busy-waiting and give time to other processes
            long sleepTime = TICK_RATE_MS - (System.currentTimeMillis() - lastTime);
            if (sleepTime > 0) {
                Thread.sleep(sleepTime);
            }
        }
    }
}
