package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

interface IGame {
    void createGameSession();
    void startGameplay() throws InterruptedException;
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

