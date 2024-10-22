package View;

import Model.*;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = 1400;
    final int screenHeight = 600;
    GameBoard gameBoard;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    private GameSession gameSession; // Reference to control the game

    // Buttons for controlling the game
    private JButton stopButton;
    private JButton resumeButton;

    public GamePanel(GameSession gameSession) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.gameBoard = gameSession.getGameBoard();
        this.gameSession = gameSession;

        // Create the control buttons and add action listeners
        setupControlButtons();
    }

    private void setupControlButtons() {
        // Initialize buttons
        stopButton = new JButton("Stop");
        resumeButton = new JButton("Resume");

        // Set button actions
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameSession.pauseGame();  // Pause the game
            }
        });

        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameSession.resumeGame();  // Resume the game
            }
        });


        // Create a control panel to hold the buttons
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        // Add buttons to the control panel
        controlPanel.add(stopButton);
        controlPanel.add(resumeButton);

        // Add the control panel to the GamePanel
        this.setLayout(new BorderLayout());
        this.add(controlPanel, BorderLayout.SOUTH);  // Place it at the bottom
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Load GIFs for entities and projectiles using Toolkit from the src/images folder
        Image backGroundDay = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/backGroundDay.gif"));
        Image peaShooterGif = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/peashooter.gif"));
        Image sunflowerGif = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/sunflower.gif"));
        Image wallNutGif = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/wallnut.gif"));
        Image projectileImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/projectile.gif"));

        int entityWidth = 40;
        int entityHeight = 40;
        int projectileWidth = 10;
        int projectileHeight = 10;

        // Draw the game board
        if (backGroundDay != null) {
            g2.drawImage(backGroundDay, 0, 0, getWidth(), getHeight(), this);
        }

        // Draw entities with corresponding animated GIFs
        for (EntityActions entity : gameBoard.getEntities()) {
            Image entityImage = null;

            switch (entity.getName()) {
                case "Pea Shooter":
                    entityImage = peaShooterGif;
                    break;
                case "Sunflower":
                    entityImage = sunflowerGif;
                    break;
                case "Wall-nut":
                    entityImage = wallNutGif;
                    break;
            }

            // If the image is loaded, draw the animated gif
            if (entityImage != null) {
                g2.drawImage(entityImage, entity.getPositionX(), entity.getPositionY(), 40, 40, this);
            } else {
                // In case image loading fails, use a fallback color and rectangle
                g2.setColor(Color.red);  // Default color for missing image
                g2.fillRect(entity.getPositionX(), entity.getPositionY(), 40, 40);
            }
        }

        // Draw projectiles as images
        for (Projectile projectile : gameBoard.getProjectiles()) {
            // If the projectile image is loaded, draw it
            if (projectileImage != null) {
                g2.drawImage(projectileImage, projectile.getPositionX()+40, projectile.getPositionY()+5, 10, 10, this);
            } else {
                // In case the image fails to load, fallback to drawing a green rectangle
                g2.setColor(Color.green);
                g2.fillRect(projectile.getPositionX(), projectile.getPositionY(), 10, 10);
            }
        }

        // Ensure the component keeps repainting to animate the GIFs
        Toolkit.getDefaultToolkit().sync(); // Synchronizes for smooth animation
    }
}
