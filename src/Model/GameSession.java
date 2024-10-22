package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameSession {
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


        Main.createGUI(this);
    }

    private void scheduleCommand(int interval, Command command) {
        scheduledCommands.add(new ScheduledCommand(interval, command));
    }

    public void restore(GameMemento memento) {
        this.tickNumber = (int) memento.getTickNumber();
        this.isRunning = memento.isRunning();
        this.isPaused = memento.isPaused();
    }

    public GameBoard getGameBoard() {
        return this.gameBoard;
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

    public void pauseGame() {
        System.out.println("Model.Game paused.");
        isPaused = true;
    }

    public void resumeGame() {
        System.out.println("Model.Game resumed.");
        isPaused = false;
    }

    public void stopGame() {
        System.out.println("Stopping the game...");
        isRunning = false;
    }

    public void startGameplay() throws InterruptedException {
        long lastTime = System.currentTimeMillis();
        startCommandListener();

        // Grid settings
        int gridRows = 5;
        int gridColumns = 9;
        int cellWidth = 100;
        int cellHeight = 125;
        int leftMargin = 350; // Distance from the left side of the game board
        int upperMargin = 125; // Distance from the upper side of the game board
        int rightMargin = 510; // Distance from the right side of the game board

        // Calculate the usable width of the game board after margins
        int usableWidth = 1400 - leftMargin - rightMargin; // Total width for the grid
        int usableHeight = 600 - upperMargin; // Total height for the grid

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
                            // Calculate a random position in the grid
                            Random rand = new Random();
                            int column = rand.nextInt(gridColumns);
                            int row = rand.nextInt(gridRows);

                            // Calculate the x and y positions based on the column and row
                            int x = leftMargin + column * cellWidth; // Calculate x position
                            int y = upperMargin + row * cellHeight; // Calculate y position

                            // Ensure that the coordinates are within the game board limits
                            if (x >= leftMargin && x + cellWidth <= 1400 - rightMargin &&
                                    y >= upperMargin && y + cellHeight <= 600) {
                                scheduledCommand.getCommand().execute(gameBoard, x, y);
                                System.out.println("Created at " + x + ":" + y);
                            }
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
