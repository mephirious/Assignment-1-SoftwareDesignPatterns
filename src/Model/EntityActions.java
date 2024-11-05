package Model;

import View.SoundEffect;

import java.util.ArrayList;
import java.util.List;

interface Actions {
    void update(GameBoard gameBoard);
}
interface EntityBehavior {
    boolean performAction(EntityActions entity, GameBoard gameBoard);
}

class AttackAndMoveBehavior implements EntityBehavior {
    @Override
    public boolean performAction(EntityActions entity, GameBoard gameBoard) {
        // Create a static projectile at the zombie's current position
        Projectile projectile = entity.getProjectile();
        projectile.setDamage(entity.getDamage());
        projectile.setTeamID(entity.getTeamID());

        // Set the projectile's position to the zombie's current position



        // Check if there is an enemy entity nearby for the zombie to attack
        boolean enemyInRange = false;
        for (EntityActions otherEntity : gameBoard.getEntities()) {
            if (otherEntity.getTeamID() != entity.getTeamID()) {
                // Check if the enemy is within a certain range
                if (isEnemyNearby(entity, otherEntity)) {
                    enemyInRange = true;
                    break;
                }
            }
        }

        if (enemyInRange) {
            // Add the stationary projectile to the game board
            gameBoard.addProjectile(projectile.clone(entity.getPositionX(), entity.getPositionY()));
            SoundEffect soundEffect = new SoundEffect("/sounds/zombieAttack.wav");
            soundEffect.setVolume(0.1f);
            soundEffect.play();
            return true;
        } else {
            // Move the zombie if no enemy is nearby
            System.out.println("Moving " + entity.getName() + " to position " + entity.getPositionX());
            entity.updatePosition();
        }

        return false;
    }

    // Helper method to check if an enemy is within a certain range
    private boolean isEnemyNearby(EntityActions entity, EntityActions otherEntity) {
        double rangeThreshold = 5.0;  // Adjust this range as needed

        double deltaX = entity.getPositionX() - otherEntity.getPositionX();
        double deltaY = entity.getPositionY() - otherEntity.getPositionY();
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        return distance <= rangeThreshold;
    }
}

class AttackBehavior implements EntityBehavior {
    @Override
    public boolean performAction(EntityActions entity, GameBoard gameBoard) {
        Projectile projectile = entity.getProjectile();
        projectile.setDamage(entity.getDamage());
        projectile.setTeamID(entity.getTeamID());

        // Calculate the end position of the projectile based on its velocity and direction
        double endPositionX = projectile.getPositionX() + projectile.getVelocityX() * projectile.getDirectionX();
        double endPositionY = projectile.getPositionY() + projectile.getVelocityY() * projectile.getDirectionY();

        // Check if there is an enemy entity on the line of the projectile's movement
        boolean enemyFound = false;
        for (Entity otherEntity : gameBoard.getEntities()) {
            if (otherEntity.getTeamID() != entity.getTeamID()) {
                // Check if the other entity is on the line of the projectile's movement
                if (isEntityOnLine(otherEntity, projectile.getPositionX(), projectile.getPositionY(), endPositionX, endPositionY)) {
                    // Check if the enemy is in the forward direction of the projectile
                    if (isEnemyInForwardDirection(projectile, otherEntity)) {
                        enemyFound = true;
                        break;
                    }
                }
            }
        }

        if (enemyFound) {
            // Proceed with adding the projectile to the game board
            gameBoard.addProjectile(projectile.clone(50, 5));
            SoundEffect soundEffect = new SoundEffect("/sounds/pea.wav");
            soundEffect.setVolume(0.2f);
            soundEffect.play();
            return true;
        }
//            else {
//                System.out.println(entity.getName() + " cannot attack without an enemy in range.");
//            }
        return false;
    }

    private boolean isEntityOnLine(Entity entity, double startX, double startY, double endX, double endY) {
        double entityX = entity.getPositionX();
        double entityY = entity.getPositionY();

        double threshold = 1.0; // Adjust this value based on your game's scale

        double distance = Math.abs((endY - startY) * entityX - (endX - startX) * entityY + endX * startY - endY * startX) /
                Math.sqrt(Math.pow(endY - startY, 2) + Math.pow(endX - startX, 2));

        return distance < threshold;
    }

    private boolean isEnemyInForwardDirection(Projectile projectile, Entity enemy) {
        double enemyX = enemy.getPositionX() - projectile.getPositionX();
        double enemyY = enemy.getPositionY() - projectile.getPositionY();

        // Calculate the dot product to check if the enemy is in the forward direction
        double dotProduct = (projectile.getDirectionX() * enemyX) + (projectile.getDirectionY() * enemyY);

        // The dot product should be positive if the enemy is in the forward direction
        return dotProduct > 0;
    }
}

class ProduceSunBehavior implements EntityBehavior {
    @Override
    public boolean performAction(EntityActions entity, GameBoard gameBoard) {
        System.out.println(entity.getName() + " is producing sun!");
        //gameBoard.spawnSun(25);
        return true;
    }
}

class PassiveBehavior implements EntityBehavior {
    @Override
    public boolean performAction(EntityActions entity, GameBoard gameBoard) {
        return true;
        //System.out.println(entity.getName() + " breathing . . .");
    }
}




class Entity extends Container {
    private String name;
    private String description;
    int health;
    private int teamID;
    private int damage;
    List<Observer> observers = new ArrayList<>();

    public Entity(int teamID, int positionX, int positionY, int width, int height, String name, String description, int health, int damage) {
        super(positionX, positionY, width, height);
        this.teamID = teamID;
        this.name = name;
        this.description = description;
        this.health = health;
        this.damage = damage;
    }

    public void setTeamID(int teamID) { this.teamID = teamID; }
    public int getTeamID() { return this.teamID; }
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    public void setDescription(String description) { this.description = description;}
    public String getDescription() { return description; }
    public void reduceHealth(int damage) { this.health = this.health - damage; }
    public void setHealth(int health) { this.health = health; }
    public int getHealth() { return health; }
    public void setDamage(int damage) { this.damage = damage; }
    public int getDamage() { return damage; }
}

public abstract class EntityActions extends Entity implements Actions {
    private Projectile projectile;
    private EntityBehavior behavior;

    public EntityActions(int teamID, int positionX, int positionY, int width, int height, String name, String description, int health, int damage, Projectile projectile, EntityBehavior behavior) {
        super(teamID, positionX, positionY, width, height, name, description, health, damage);
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
