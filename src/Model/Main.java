package Model;

import View.GamePanel;

import javax.swing.*;

public class Main {

    public static void createGUI(GameBoard gameBoard) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Plants vs Zombies");

        GamePanel gamePanel = new GamePanel(gameBoard);
        window.add(gamePanel);
        gamePanel.startGameThread();
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public static void main(String[] args) {
        Game game = Game.getInstance();

        // TODO: Model.GameBoard Generator, ex Sun value, Plants to plant(with planting cooldown), PlantingGrid(where plants are placed and zombies moving), zombiesPre-Spawning area
        game.createGameSession();

        try {
            game.startGameplay();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}