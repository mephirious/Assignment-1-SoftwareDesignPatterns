import java.util.ArrayList;
import java.util.List;

interface IGame {
    void createGameSession();
    void startGameplay() throws InterruptedException;
}
interface Command {
    void execute(GameBoard gameBoard, int x, int y);
}

class GameSession {
    private GameBoard gameBoard;
    private int tickNumber;
    private volatile boolean isRunning;
    private static final int TICK_RATE_MS = 50;
    private List<ScheduledCommand> scheduledCommands;

    public void initialize() {
        gameBoard = new GameBoard();
        tickNumber = 0;
        isRunning = true;

        scheduledCommands = new ArrayList<>();
        // Schedule commands
        scheduleCommand(100, new AddPeashooterCommand());
        scheduleCommand(150, new AddWallnutCommand());
        scheduleCommand(200, new AddSunflowerCommand());
    }

    private void scheduleCommand(int interval, Command command) {
        scheduledCommands.add(new ScheduledCommand(interval, command));
    }


    private static class ScheduledCommand implements Command{
        private final int interval;
        private final Command command;

        public ScheduledCommand(int interval, Command command) {
            this.interval = interval;
            this.command = command;
        }

        public int getInterval() {
            return interval;
        }

        @Override
        public void execute(GameBoard gameBoard, int x, int y) {
            command.execute(gameBoard, x, y);
        }

        public Command getCommand() {
            return command;
        }
    }
    public void startGameplay() throws InterruptedException {
        long lastTime = System.currentTimeMillis();

        while (isRunning) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - lastTime;

            if (elapsedTime >= TICK_RATE_MS) {
                tickNumber += 1;
                boolean continueGame = gameBoard.Tick();

                // Execute scheduled commands
                for (ScheduledCommand scheduledCommand : scheduledCommands) {
                    if (tickNumber % scheduledCommand.getInterval() == 0) {
                        scheduledCommand.getCommand().execute(gameBoard, 100, 200);
                    }
                }

                if (!continueGame) {
                    isRunning = false;
                    break;
                }

                lastTime = currentTime;
            }

            long sleepTime = TICK_RATE_MS - (System.currentTimeMillis() - lastTime);
            if (sleepTime > 0) {
                Thread.sleep(sleepTime);
            }
        }
    }
}

public class Game implements IGame {
    private static Game instance;
    private GameSession gameSession;

    private Game() {}

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    @Override
    public void createGameSession() {
        this.gameSession = new GameSession();
        this.gameSession.initialize();
    }

    @Override
    public void startGameplay() throws InterruptedException {
        if (gameSession != null) {
            gameSession.startGameplay();
        }
    }
}

class AddPeashooterCommand implements Command {
    @Override
    public void execute(GameBoard gameBoard, int x, int y) {
        Plant peashooter = Plant.Director.constructPeaShooter();
        peashooter.setPositionX(x);
        peashooter.setPositionY(y);
        peashooter.getProjectile().setPositionX(x);
        peashooter.getProjectile().setPositionY(y);
        gameBoard.addEntity(peashooter);
    }
}

class AddWallnutCommand implements Command {
    @Override
    public void execute(GameBoard gameBoard, int x, int y) {
        Plant wallnut = Plant.Director.constructWallNut();
        wallnut.setPositionX(x);
        wallnut.setPositionY(y);
        wallnut.getProjectile().setPositionX(x);
        wallnut.getProjectile().setPositionY(y);
        gameBoard.addEntity(wallnut);
    }
}

class AddSunflowerCommand implements Command {
    @Override
    public void execute(GameBoard gameBoard, int x, int y) {
        Plant sunflower = Plant.Director.constructSunflower();
        sunflower.setPositionX(x);
        sunflower.setPositionY(y);
        sunflower.getProjectile().setPositionX(x);
        sunflower.getProjectile().setPositionY(y);
        gameBoard.addEntity(sunflower);
    }
}
