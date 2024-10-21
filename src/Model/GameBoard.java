import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

interface IGameBoard {
    void addEntity(EntityActions entity);
    void addProjectile(Projectile projectile);
}

public class GameBoard implements IGameBoard {
    private final List<EntityActions> entities;
    private final List<Projectile> projectiles;
    private boolean braindead;

    public GameBoard() {
        this.entities = new ArrayList<>();
        this.projectiles = new ArrayList<>();
        this.braindead = false;
    }

    public void addEntity(EntityActions entity) { entities.add(entity); }
    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
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
