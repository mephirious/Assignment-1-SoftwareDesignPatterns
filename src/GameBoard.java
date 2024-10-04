import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

interface IGameBoard {
    void addEntity(Entity entity);
    void addProjectile(Projectile projectile);
}

public class GameBoard implements IGameBoard {
    private final List<Entity> entities;
    private final List<Projectile> projectiles;
    private boolean braindead;

    public GameBoard() {
        this.entities = new ArrayList<>();
        this.projectiles = new ArrayList<>();
        this.braindead = false;
    }

    public void addEntity(Entity entity) { this.entities.add(entity); }
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
            p.updatePosition();

            // If collision or out-of-bounds, remove the projectile
            if (p.checkCollision() || isOutOfBounds(p)) {
                iterator.remove();
            }
        }

        if (this.braindead) {
            return false;
        }

        return true;

        // return !this.braindead;
    }

    private boolean isOutOfBounds(Projectile p) {
        // TODO: for projectiles off screen
        return true;
    }
}
