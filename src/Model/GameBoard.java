package Model;

import View.SoundEffect;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

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

    public boolean update() {
        entityManager.updateEntities();
        projectileManager.updateProjectiles(gameBoard.getEntities());
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
        int cellWidth = 80;
        int cellHeight = 100;
        int leftMargin = 60; // Distance from the left side of the game board
        int upperMargin = 80; // Distance from the upper side of the game board
        int rightMargin = 0; // Distance from the right side of the game board

        // Get the x and y coordinates from the entity's current position
        int x = entity.getPositionX();
        int y = entity.getPositionY();

        // Ensure that the coordinates are within the game board limits
        if (x >= leftMargin && x + cellWidth <= 900 - rightMargin &&
                y >= upperMargin && y + cellHeight / 2 <= 650) {

            // Calculate the grid cell coordinates for the entity's position
            int col = (x - 100) / cellWidth;
            int row = (y - 120) / cellHeight;

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
        List<EntityActions> toRemove = new ArrayList<>();

        Iterator<EntityActions> iterator = gameBoard.getEntities().iterator();
        while (iterator.hasNext()) {
            EntityActions e = iterator.next();
            e.update(gameBoard);

            if (e.getHealth() <= 0) {
                System.out.println(e.getName() + " i am ded :(");
                SoundEffect soundEffect = new SoundEffect("/sounds/peaHit.wav");
                soundEffect.play();
                toRemove.add(e);
            }
            if (e.getPositionX() < -10) {
                this.braindead = true;
            }
        }

        // Remove entities outside of the iteration loop
        gameBoard.getEntities().removeAll(toRemove);
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

    public void updateProjectiles(List<EntityActions> entities) {
        Iterator<Projectile> iterator = gameBoard.getProjectiles().iterator();
        while (iterator.hasNext()) {
            Projectile p = iterator.next();
            p.updatePosition();
    
            if (p.checkCollision(entities)) {
                SoundEffect soundEffect = new SoundEffect("/sounds/peaHit.wav");
                soundEffect.setVolume(0.1f);
                soundEffect.play();
                iterator.remove();
            }
            if (isOutOfBounds(p)) {
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
    private final CopyOnWriteArrayList<EntityActions> entities;
    private final ArrayList<Projectile> projectiles;

    public GameBoard(int positionX, int positionY) {
        super(positionX, positionY, 900, 600);
        this.entities = new CopyOnWriteArrayList<>();
        this.projectiles = new ArrayList<>();
    }

    public boolean addEntity(EntityActions entity) {
        return !new EntityManager(this).addEntity(entity);
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

    public void clearBoard() {
        this.entities.clear();
        this.projectiles.clear();
    }


}