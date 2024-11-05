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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int screenWidth = 900;
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
        setupMouseListener();

        // Add the plant selection menu
        PlantSelectionMenu plantSelectionMenu = new PlantSelectionMenu(this);
        this.add(plantSelectionMenu, BorderLayout.NORTH); // Add it at the top

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
                drawCount = 0;
                timer = 0;
            }
        }

    }

    public void update() {

    }

    public static void endGame(String message, GameSession gameSession) {
        // Create a panel to add custom buttons
        JPanel panel = new JPanel();
        JLabel label = new JLabel(message);
        panel.add(label);

        // Create a custom button for restarting gameplay
        JButton restartButton = new JButton("Restart Game");
        panel.add(restartButton);

        // Define the action for the restart button
        restartButton.addActionListener(e -> {
            try {
                gameSession.startGameplay();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            // Close the dialog after the button is pressed
            Window window = SwingUtilities.getWindowAncestor(panel);
            if (window != null) {
                window.dispose();
            }
        });

        // Show the custom dialog with the message and restart button
        JOptionPane.showOptionDialog(
                null,
                panel,
                "Game Over",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new Object[]{},  // Empty array for options removes default buttons
                null
        );
    }


    public void removeEntity(EntityActions entity) {
        gameBoard.getEntities().remove(entity); // Assuming entities is a List<EntityActions>
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
        Image adaynutGif = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/zombie.gif"));
        Image wallNutGif = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/wallnut.gif"));
        Image peaImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/projectile.gif"));
        Image sunImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/sun.gif"));

        int entityWidth = 40;
        int entityHeight = 40;
        int projectileWidth = 10;
        int projectileHeight = 10;

        // Draw the game board
        if (backGroundDay != null) {
            g2.drawImage(backGroundDay, -200, 0, 1400, 600, this);
        }

        int width = 80;
        int height = 80;
        // Draw entities with corresponding animated GIFs
        for (EntityActions entity : gameBoard.getEntities()) {
            String entityName = entity.getName();
            if (entityName == null) {
                System.out.println("Entity name is null. Skipping rendering for this entity.");
                continue; // Skip rendering this entity
            }

            Image entityImage = null;
            switch (entityName) {
                case "Pea Shooter":
                    entityImage = peaShooterGif;
                    break;
                case "Sunflower":
                    entityImage = sunflowerGif;
                    break;
                case "Wall-nut":
                    entityImage = wallNutGif;
                    break;
                case "Aday-nut":
                    entityImage = adaynutGif;
                    break;
            }

            if (entityImage != null) {
                switch (entity.getTeamID()) {
                    case 0 :
                        g2.setColor(Color.orange);
                        break;
                    default:
                        g2.setColor(Color.red);
                        break;
                }
//                g2.fillRect(entity.getPositionX(), entity.getPositionY(), entity.getWidth(), entity.getHeight());
                g2.drawImage(entityImage, entity.getPositionX(), entity.getPositionY(), entity.getWidth(), entity.getHeight(), this);
//                g2.fillRect(entity.getPositionX(), entity.getPositionY(), 40, 40);// Draw the HP below the entity image
                String hpText = "HP: " + entity.getHealth(); // Assuming there's a method getHP() in the entity class
                g2.drawString(hpText, entity.getPositionX() - 80 / 2, entity.getPositionY() + 80 / 2 + 15); // Adjust the Y position as needed

            } else {
                g2.setColor(Color.red);
                g2.fillRect(entity.getPositionX(), entity.getPositionY(), 40, 40);
            }
        }


        // Draw projectiles as images
        for (Projectile projectile : gameBoard.getProjectiles()) {
            String projectileName = projectile.getName();
            Image projectileImage = null;

            if (projectileName == null) {
//                System.out.println("Projectile name is null. Skipping rendering for this projectile.");
            } else {
                switch (projectileName) {
                    case "Pea":
                        projectileImage = peaImage;
                        break;
                }
            }

            if (projectileImage != null) {
                switch (projectile.getTeamID()) {
                    case 0 :
                        g2.setColor(Color.orange);
                        break;
                    default:
                        g2.setColor(Color.red);
                        break;
                }
//                g2.fillRect(projectile.getPositionX()-width/2 + 50, projectile.getPositionY()-width/2 + 5, 30, 30);
//                g2.fillRect(projectile.getPositionX(), projectile.getPositionY(), projectile.getWidth(), projectile.getHeight());
                g2.drawImage(projectileImage, projectile.getPositionX(), projectile.getPositionY(), projectile.getWidth(), projectile.getHeight(), this);
//                String hpText = "DMG: " + projectile.getDamage(); // Assuming there's a method getHP() in the entity class
//                g2.drawString(hpText, projectile.getPositionX() - 30 / 2, projectile.getPositionY() + 30 / 2); // Adjust the Y position as needed
            } else {
                g2.setColor(Color.green);
                g2.fillRect(projectile.getPositionX()-width/2 + 50, projectile.getPositionY()-width/2 + 5, 30, 30);
            }
        }

        // Ensure the component keeps repainting to animate the GIFs
        Toolkit.getDefaultToolkit().sync(); // Synchronizes for smooth animation
    }

    private String currentPlant = null; // The currently selected plant type

    public void setCurrentPlant(String plantName) {
        this.currentPlant = plantName; // Set the currently selected plant
    }

    private void setupMouseListener() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (currentPlant != null) {
                    int x = e.getX()+40;
                    int y = e.getY()+40;
                    placePlant(currentPlant, x, y); // Place the selected plant
                }
            }
        });
    }

    private void placePlant(String plantName, int x, int y) {
        // Use the command pattern to add the selected plant to the game board
        Command command;
        switch (plantName) {
            case "Peashooter":
                command = new AddPeashooterCommand();
                break;
            case "Wall-nut":
                command = new AddWallnutCommand();
                break;
            case "Sunflower":
                command = new AddSunflowerCommand();
                break;
            case "Aday-nut":
                command = new AddAdaynutCommand();
                break;
            // Add cases for other plants as needed
            default:
                return; // Invalid plant name
        }
        command.execute(gameBoard, x, y); // Execute the command to place the plant
    }
}
