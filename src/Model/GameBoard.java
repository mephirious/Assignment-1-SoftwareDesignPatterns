package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

interface IGameBoard {
    boolean addEntity(EntityActions entity);
    void addProjectile(Projectile projectile);
}

public class GameBoard extends Container implements IGameBoard {
    private final List<EntityActions> entities;
    private final List<Projectile> projectiles;
    private boolean braindead;
    /*
        Tile Size f.e. 40x40px => 360x200px board
         0 1 2 3 4 5 6 7 8
      0  * * * * * * * * *
      1  * * * * * * * * *
      2  * * * * * * * * *
      3  * * * * * * * * *
      4  * * * * * * * * *

     */
    public GameBoard(int positionX, int positionY) {
        super( positionX, positionY);
        this.entities = new ArrayList<>();
        this.projectiles = new ArrayList<>();
        this.braindead = false;
        Main.createGUI(this);
    }

    public boolean addEntity(EntityActions entity) {
        for (EntityActions e : entities) {
            if (e.getPositionX() == entity.getPositionX() &&
                e.getPositionY() == entity.getPositionY()) {
                return false;
            }
        }
        entities.add(entity);
        return true;
    }
    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
    }

    public List<EntityActions> getEntities() {
        return entities;
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    // TODO: need to separate shooting, movement, etc .
    public boolean Tick() {
        for (EntityActions entity : entities) {
            entity.update(this);

            if (entity.getPositionX() < -10) {
                this.braindead = true;
            }
        }
        updateProjectiles();
        return !this.braindead;
    }

    private void updateProjectiles() {
        Iterator<Projectile> iterator = projectiles.iterator();
        while (iterator.hasNext()) {
            Projectile p = iterator.next();
            p.updatePosition();
            if (p.checkCollision() || isOutOfBounds(p)) {
                iterator.remove();
            }
        }
    }

    private boolean isOutOfBounds(Projectile p) {
        return p.getPositionX() < 0 || p.getPositionX() > 800 || p.getPositionY() < 0 || p.getPositionY() > 600;
    }
}
