package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

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
    private boolean isPaused = false;

    public void initialize() {
        gameBoard = new GameBoard(60, 100);
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

    public void restore(GameMemento memento) {
        this.tickNumber = (int) memento.getTickNumber();
        this.isRunning = memento.isRunning();
        this.isPaused = memento.isPaused();
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

    private void startCommandListener() {
        Thread commandThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);

            while (isRunning) {
                String command = scanner.nextLine();

                if (command.equalsIgnoreCase("stop")) {
                    pauseGame();
                } else if (command.equalsIgnoreCase("resume")) {
                    resumeGame();
                } else if (command.equalsIgnoreCase("exit")) {
                    stopGame();
                }
            }
        });

        commandThread.setDaemon(true);
        commandThread.start();
    }

    private void pauseGame() {
        System.out.println("Model.Game paused.");
        isPaused = true;
    }

    private void resumeGame() {
        System.out.println("Model.Game resumed.");
        isPaused = false;
    }

    private void stopGame() {
        System.out.println("Stopping the game...");
        isRunning = false;
    }

    public void startGameplay() throws InterruptedException {
        long lastTime = System.currentTimeMillis();

        startCommandListener();

        while (isRunning) {
            if (!isPaused) {
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - lastTime;

                if (elapsedTime >= TICK_RATE_MS) {
                    tickNumber += 1;
                    boolean continueGame = gameBoard.Tick();

                    // Execute scheduled commands
                    for (ScheduledCommand scheduledCommand : scheduledCommands) {
                        if (tickNumber % scheduledCommand.getInterval() == 0) {
                            Random rand = new Random();
                            int x = rand.nextInt(0,9)*(40+20);
                            int y = rand.nextInt(0,5)*(40+20);
                            scheduledCommand.getCommand().execute(gameBoard, x+60, y+100);
                            System.out.println("Created at" + x + ":" + y);
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

    public GameSession getGameSession() {
        return gameSession;
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
        if (! gameBoard.addEntity(peashooter)) {
            System.out.println("Cannot create " + peashooter.getName() + " at " + x + ":" +y);
        }
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
        if (! gameBoard.addEntity(wallnut)) {
            System.out.println("Cannot create " + wallnut.getName() + " at " + x + ":" +y);
        }
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
        if (! gameBoard.addEntity(sunflower)) {
            System.out.println("Cannot create " + sunflower.getName() + " at " + x + ":" +y);
        }
    }
}
