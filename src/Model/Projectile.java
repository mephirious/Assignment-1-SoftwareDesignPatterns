package Model;

import java.util.List;

interface IProjectile {
    Projectile clone(int marginX, int marginY);
}

public class Projectile extends Container implements IProjectile{
    private String name;
    private int damage;
    private int teamID;

    public Projectile(int positionX, int positionY, int directionX, int directionY, int velocityX, int velocityY, String name) {
        super(positionX, positionY, 30, 30);
        setVelocityX(velocityX);
        setVelocityY(velocityY);
        setDirectionX(directionX);
        setDirectionY(directionY);
        this.name = name;
        this.damage = 0;
    }
    public void setTeamID(int teamID) { this.teamID = teamID; }
    public int getTeamID() { return teamID; }
    public String getName() { return name; }
    public void setDamage(int damage) { this.damage = damage; }
    public int getDamage() { return damage; }

    public boolean checkCollision(List<EntityActions> entities) {
        for (EntityActions entity : entities) {
            if (entity.getTeamID() != this.teamID) { // Check for different team IDs
                // Implement your collision detection logic here
                if (isCollidingWith(entity)) {
                    entity.reduceHealth(this.damage);
//                    System.out.println(entity.getName() + " took damage of " + this.damage);
                    return true; // Collision detected
                }
            }
        }
        return false; // No collision
    }

    private boolean isCollidingWith(EntityActions entity) {
        // Get this entity's position and dimensions
        int thisX = this.getPositionX();
        int thisY = this.getPositionY();
        int thisWidth = this.getWidth();
        int thisHeight = this.getHeight();

        // Get the other entity's position and dimensions
        int entityX = entity.getPositionX();
        int entityY = entity.getPositionY();
        int entityWidth = entity.getWidth();
        int entityHeight = entity.getHeight();

        // Check for overlap between the two rectangles
        boolean xOverlap = thisX < entityX + entityWidth && thisX + thisWidth > entityX;
        boolean yOverlap = thisY < entityY + entityHeight && thisY + thisHeight > entityY;

        // Collision occurs if both x and y overlaps are true
        return xOverlap && yOverlap;
    }

    // Prototype design pattern
    public Projectile clone(int marginX, int marginY) {
        Projectile projectile = new Projectile(getPositionX()+marginX, getPositionY()+marginY, getDirectionX(), getDirectionY(), getVelocityX(), getVelocityX(), getName());
        projectile.setDamage(damage);
        projectile.setTeamID(teamID);
        return projectile;
    }
}