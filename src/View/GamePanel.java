package View;

import Model.EntityActions;
import Model.Game;
import Model.GameBoard;
import Model.Projectile;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    GameBoard gameBoard;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    public GamePanel(GameBoard gameBoard) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.gameBoard = gameBoard;
    }

    @Override
    public void run() {
        int FPS = 240;
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                // UPDATE entities
                update();
                // DRAW the screen with updated information
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS:"+drawCount);
                drawCount = 0;
                timer = 0;
            }
        }

    }

    public void update() {
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.darkGray);

        g2.fillRect(gameBoard.getPositionX(), gameBoard.getPositionY(), (40+20)*9, (40+20)*5);

        for (EntityActions entity : gameBoard.getEntities()) {
            switch (entity.getName()) {
                case "Pea Shooter" : g2.setColor(Color.green); break;
                case "Sunflower" : g2.setColor(Color.yellow); break;
                case "Wall-nut" : g2.setColor(Color.orange); break;
            }
            g2.fillRect(entity.getPositionX(), entity.getPositionY(), 40, 40);

        }

        for (Projectile projectile : gameBoard.getProjectiles()) {
            g2.setColor(Color.green);
            g2.fillRect(projectile.getPositionX(), projectile.getPositionY(), 10, 10);
        }

    }
}
