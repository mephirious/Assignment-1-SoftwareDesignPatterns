package Model;

import java.util.ArrayList;
import java.util.List;

interface Actions {
    void update(GameBoard gameBoard);
}
interface EntityBehavior {
    void performAction(EntityActions entity, GameBoard gameBoard);
}
class AttackBehavior implements EntityBehavior {
    @Override
    public void performAction(EntityActions entity, GameBoard gameBoard) {
        Projectile projectile = entity.getProjectile();
        if (projectile != null) {
            System.out.println(entity.getName() + " is attacking!");
            gameBoard.addProjectile(projectile.clone());
        } else {
            System.out.println(entity.getName() + " cannot attack without a projectile.");
        }
    }
}

class ProduceSunBehavior implements EntityBehavior {
    @Override
    public void performAction(EntityActions entity, GameBoard gameBoard) {
        System.out.println(entity.getName() + " is producing sun!");
        //gameBoard.spawnSun(25);
    }
}

class PassiveBehavior implements EntityBehavior {
    @Override
    public void performAction(EntityActions entity, GameBoard gameBoard) {
        //System.out.println(entity.getName() + " breathing . . .");
    }
}




class Entity extends Container {
    private String name;
    private String description;
    int health;
    private int damage;
    List<Observer> observers = new ArrayList<>();

    public Entity(int positionX, int positionY, String name, String description, int health, int damage, Projectile projectile) {
        super(positionX, positionY);
        this.name = name;
        this.description = description;
        this.health = health;
        this.damage = damage;
    }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    public void setDescription(String description) { this.description = description;}
    public String getDescription() { return description; }
    public void setHealth(int health) { this.health = health; }
    public int getHealth() { return health; }
    public void setDamage(int damage) { this.damage = damage; }
    public int getDamage() { return damage; }
}

public abstract class EntityActions extends Entity implements Actions {
    private Projectile projectile;
    private EntityBehavior behavior;

    public EntityActions(int positionX, int positionY, String name, String description, int health, int damage, Projectile projectile, EntityBehavior behavior) {
        super(positionX, positionY, name, description, health, damage, projectile);
        this.projectile = projectile;
        this.behavior = behavior;
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.updateHealth(this.getName(), this.getHealth());
        }
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void changeHealth(int amount) {
        this.health += amount;
        notifyObservers();
    }

    public void setProjectile(Projectile projectile) { this.projectile = projectile; }
    public Projectile getProjectile() { return projectile; }

    public void setBehavior(EntityBehavior behavior) { this.behavior = behavior; }
    public EntityBehavior getBehavior() { return this.behavior; }

    @Override
    public void update(GameBoard gameBoard) {
        behavior.performAction(this, gameBoard);
    }
}
