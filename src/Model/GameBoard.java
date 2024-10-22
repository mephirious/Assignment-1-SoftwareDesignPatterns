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
        for (EntityActions e : gameBoard.getEntities()) {
            if (e.getPositionX() == entity.getPositionX() &&
                    e.getPositionY() == entity.getPositionY()) {
                return false;
            }
        }
        gameBoard.getEntities().add(entity);
        return true;
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
        return p.getPositionX() < 0 || p.getPositionX() > 800 ||
                p.getPositionY() < 0 || p.getPositionY() > 600;
    }
}

// Modified GameBoard class
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
}