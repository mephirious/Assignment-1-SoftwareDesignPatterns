interface IProjectile {
    Projectile clone();
}

public class Projectile extends Container implements IProjectile{
    private int directionX;
    private int directionY;
    private int velocityX;
    private int velocityY;

    public Projectile(int positionX, int positionY, int directionX, int directionY, int velocityX, int velocityY) {
        super(positionX, positionY);
        this.directionX = directionX;
        this.directionY = directionY;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

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

    public void updatePosition() {
        int newPositionX = getPositionX() + getVelocityX() * getDirectionX();
        int newPositionY = getPositionY() + getVelocityY() * getDirectionY();
        setPositionX(newPositionX);
        setPositionY(newPositionY);
    }

    public boolean checkCollision() {
        // TODO: implement a function that will check the collision correctly
        return false;
    }

    // Prototype design pattern
    public Projectile clone() {
        return new Projectile(getPositionX(), getPositionY(), getDirectionX(), getDirectionY(), getVelocityX(), getVelocityX());
    }
}