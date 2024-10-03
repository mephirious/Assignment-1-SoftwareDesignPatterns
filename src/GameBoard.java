import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Random;

public class GameBoard extends JPanel {
    private final List<Entity> entities;
    private final List<Projectile> projectiles;

    // Size of the window
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;


    private boolean braindead;

    public GameBoard() {
        this.entities = new ArrayList<>();
        this.projectiles = new ArrayList<>();
        this.braindead = false;
    }

    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }
    public void addProjectile(Projectile projectile) {
        this.projectiles.add(projectile);
    }

    // TODO: need to separate shooting, movement, etc .
    public boolean Tick() {
        for (Entity entity : entities) {
            entity.update(this);

            if (entity.getPositionX() < -10) {
                this.braindead = true;
            }
        }

        // Update projectiles
        Iterator<Projectile> iterator = projectiles.iterator();
        while (iterator.hasNext()) {
            Projectile p = iterator.next();
            p.update();

            // If collision or out-of-bounds, remove the projectile
            if (p.checkCollision() || isOutOfBounds(p)) {
                System.out.println("DELETED at: " + p.getPositionX() + ":" + p.getPositionY());
                iterator.remove();
            }
        }

        // TODO: Remove this
        repaint();

        if (this.braindead) {
            return false;
        }

        return true;

        // return !this.braindead;
    }

    private boolean isOutOfBounds(Projectile p) {
        // TODO: for projectiles off screen
        return p.getPositionX() > WINDOW_WIDTH;
    }

    // This is where we draw the entities and projectiles on the screen
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.darkGray);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        // Draw entities as green squares
        g.setColor(Color.GREEN);
        for (Entity entity : entities) {
            g.fillRect(entity.getPositionX(), entity.getPositionY(), 40, 40); // 40x40 green square
        }

        // Draw projectiles as yellow circles
        g.setColor(Color.YELLOW);
        Iterator<Projectile> iterator = projectiles.iterator();
        while (iterator.hasNext()) {
            Projectile p = iterator.next();
            g.fillOval(p.getPositionX(), p.getPositionY(), 20, 20); // 20x20 yellow circle
        }
    }

    // TODO: ONLY FOR EXAMPLE VISUALISATION, NEEDS TO BE DELETED
    public static void main(String[] args) throws InterruptedException {
        class PeashooterGenerator {
            private static Random rand = new Random();
            public static Plant generate() {
                int randX = rand.nextInt(10)*(40+40);
                int randY = rand.nextInt(5)*(40+40)+100;
                // Add some example entities and projectiles for demonstration
                return new Plant.Builder()
                        .setPositionX(randX)
                        .setPositionY(randY)
                        .setName("Pea Shooter")
                        .setDescription("A plant that shoots peas")
                        .setHealth(300)
                        .setDamage(20)
                        .setProjectile(
                                new Projectile(randX, randY, 10, 1, 0, 50, 5)).
                        setAverageActionSpeed(1.425)
                        .build();
            }
        }
        int tickNumber = 0;
        JFrame frame = new JFrame("Simple Game Visualization");
        GameBoard gameBoard = new GameBoard();

        frame.add(gameBoard);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);




        // Simple game loop for demonstration
        while (true) {
            if (tickNumber % 80 == 0) {
                gameBoard.addEntity(PeashooterGenerator.generate());
            }
            tickNumber+=1;
            gameBoard.Tick();
            // Repaint the screen to visualize updated positions
            Thread.sleep(50); // 50ms for each tick (20 FPS)
        }
    }
}
