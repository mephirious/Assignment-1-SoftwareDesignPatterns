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

public class Game implements IGame {
    private static Game instance;
    private GameSession gameSession;
    private GameBoard gameBoard;

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
//            System.out.println("Cannot create " + peashooter.getName() + " at " + x + ":" +y);
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
//            System.out.println("Cannot create " + wallnut.getName() + " at " + x + ":" +y);
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
//            System.out.println("Cannot create " + sunflower.getName() + " at " + x + ":" +y);
        }
    }
}
