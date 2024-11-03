package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

interface IGameBoard {
    boolean addEntity(EntityActions entity);
    void addProjectile(Projectile projectile);
}

// Facade class that provides a simplified interface to the game subsystem
class GameFacade {
    private final GameBoard gameBoard;
    private final EntityManager entityManager;
    private final ProjectileManager projectileManager;

    public GameFacade(int positionX, int positionY) {
        this.gameBoard = new GameBoard(positionX, positionY);
        this.entityManager = new EntityManager(gameBoard);
        this.projectileManager = new ProjectileManager(gameBoard);
    }

    // Simplified methods for client code
    public boolean addEntity(EntityActions entity) {
        return entityManager.addEntity(entity);
    }

    public void addProjectile(Projectile projectile) {
        projectileManager.addProjectile(projectile);
    }

    public boolean update() {
        entityManager.updateEntities();
        projectileManager.updateProjectiles();
        return !entityManager.isBraindead();
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }
}

// Manager class for handling entities
class EntityManager {
    private final GameBoard gameBoard;
    private boolean braindead;

    public EntityManager(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.braindead = false;
    }

    public boolean addEntity(EntityActions entity) {

        // Grid settings
        int gridRows = 5;
        int gridColumns = 9;
        int cellWidth = 80;
        int cellHeight = 100;
        int leftMargin = 100; // Distance from the left side of the game board
        int upperMargin = 120; // Distance from the upper side of the game board
        int rightMargin = 50; // Distance from the right side of the game board

        // Calculate the usable width and height of the game board after margins
        int usableWidth = 900 - leftMargin - rightMargin;
        int usableHeight = 600 - upperMargin;

        // Get the x and y coordinates from the entity's current position
        int x = entity.getPositionX();
        int y = entity.getPositionY();

        // Ensure that the coordinates are within the game board limits
        if (x >= leftMargin && x + cellWidth <= 900 - rightMargin &&
                y >= upperMargin && y + cellHeight / 2 <= 700) {

            // Calculate the grid cell coordinates for the entity's position
            int col = (x - leftMargin) / cellWidth;
            int row = (y - upperMargin) / cellHeight;

            // Snap the entity's position to the nearest cell in the grid
            int snappedX = leftMargin + col * cellWidth;
            int snappedY = upperMargin + row * cellHeight;

            // Update entity's position to the snapped coordinates
            entity.setPositionX(snappedX);
            entity.setPositionY(snappedY);

            Projectile newProjectile = entity.getProjectile();
            newProjectile.setPositionX(snappedX);
            newProjectile.setPositionY(snappedY);
            entity.setProjectile(newProjectile);

            // Check if an entity already exists at this snapped position
            for (EntityActions e : gameBoard.getEntities()) {
                if (e.getPositionX() == snappedX && e.getPositionY() == snappedY) {
                    // Return false if there is a conflict at this position
                    return false;
                }
            }

            // Add the entity to the game board if the position is free
            gameBoard.getEntities().add(entity);
            return true;
        }

        // Return false if the position is out of bounds
        return false;
    }


    public void updateEntities() {
        for (EntityActions entity : gameBoard.getEntities()) {
            entity.update(gameBoard);

            if (entity.getPositionX() < -10) {
                this.braindead = true;
            }
        }
    }

    public boolean isBraindead() {
        return braindead;
    }
}

// Manager class for handling projectiles
class ProjectileManager {
    private final GameBoard gameBoard;

    public ProjectileManager(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void addProjectile(Projectile projectile) {
        gameBoard.getProjectiles().add(projectile);
    }

    public void updateProjectiles() {
        Iterator<Projectile> iterator = gameBoard.getProjectiles().iterator();
        while (iterator.hasNext()) {
            Projectile p = iterator.next();
            p.updatePosition();
            if (p.checkCollision() || isOutOfBounds(p)) {
                iterator.remove();
            }
        }
    }

    private boolean isOutOfBounds(Projectile p) {
        return p.getPositionX() < 0 || p.getPositionX() > 900 ||
                p.getPositionY() < 0 || p.getPositionY() > 600;
    }
}

public class GameBoard extends Container implements IGameBoard {
    private final List<EntityActions> entities;
    private final List<Projectile> projectiles;

    public GameBoard(int positionX, int positionY) {
        super(positionX, positionY);
        this.entities = new ArrayList<>();
        this.projectiles = new ArrayList<>();
    }

    public boolean addEntity(EntityActions entity) {
        return new EntityManager(this).addEntity(entity);
    }

    public void addProjectile(Projectile projectile) {
        new ProjectileManager(this).addProjectile(projectile);
    }

    public List<EntityActions> getEntities() {
        return entities;
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    public void spawnSun(int value, int posX, int posY) {

    }

}