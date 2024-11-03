package Model;

import java.util.List;

interface IProjectile {
    Projectile clone();
}

public class Projectile extends Container implements IProjectile{
    private String name;
    private int damage;
    private int teamID;
    private int directionX;
    private int directionY;
    private int velocityX;
    private int velocityY;

    public Projectile(int positionX, int positionY, int directionX, int directionY, int velocityX, int velocityY, String name) {
        super(positionX, positionY);
        this.directionX = directionX;
        this.directionY = directionY;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.name = name;
        this.damage = 0;
    }
    public void setTeamID(int teamID) { this.teamID = teamID; }
    public int getTeamID() { return teamID; }
    public void setDirectionX(int directionX) { this.directionX = directionX; }
    public int getDirectionX() { return this.directionX; }
    public void setDirectionY(int directionY) { this.directionY = directionY; }
    public int getDirectionY() { return this.directionY; }
    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }
    public int getVelocityX() { return this.velocityX; }
    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }
    public int getVelocityY() { return this.velocityY; }
    public String getName() { return name; }
    public void setDamage(int damage) { this.damage = damage; }
    public int getDamage() { return damage; }

    public void updatePosition() {
        int newPositionX = getPositionX() + getVelocityX() * getDirectionX();
        int newPositionY = getPositionY() + getVelocityY() * getDirectionY();
        setPositionX(newPositionX);
        setPositionY(newPositionY);
    }

    public boolean checkCollision(List<EntityActions> entities) {
        for (EntityActions entity : entities) {
            if (entity.getTeamID() != this.teamID) { // Check for different team IDs
                // Implement your collision detection logic here
                if (isCollidingWith(entity)) {
                    entity.reduceHealth(this.damage);
                    System.out.println(entity.getName() + " took damage of " + this.damage);
                    return true; // Collision detected
                }
            }
        }
        return false; // No collision
    }

    private boolean isCollidingWith(EntityActions entity) {
        // Implement your specific collision detection logic
        // For example, check if the projectile's position overlaps with the entity's position
        if (this.getPositionY() == entity.getPositionY()) {
            if (this.getPositionX() == entity.getPositionX()) {
                return true; // Placeholder
            }
        }
        return false; // Placeholder
    }

    // Prototype design pattern
    public Projectile clone() {
        Projectile projectile = new Projectile(getPositionX(), getPositionY(), getDirectionX(), getDirectionY(), getVelocityX(), getVelocityX(), getName());
        projectile.setDamage(damage);
        projectile.setTeamID(teamID);
        return projectile;
    }
}