public class Projectile extends Container{
    private int damage;
    private int directionX;
    private int directionY;
    private int velocityX;
    private int velocityY;

    public Projectile(int positionX, int positionY, int damage, int directionX, int directionY, int velocityX, int velocityY) {
        super(positionX, positionY);
        this.damage = damage;
        this.setPositionX(positionX);
        this.setPositionY(positionY);
        this.directionX = directionX;
        this.directionY = directionY;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public void update() {
        this.setPositionX(this.getPositionX() + this.velocityX * this.directionX);
        this.setPositionY(this.getPositionY() + this.velocityY * this.directionY);
    }

    public boolean checkCollision() {
        return false;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public Projectile clone() {
        return new Projectile(this.getPositionX(), this.getPositionY(), this.damage, this.directionX, this.directionY, this.velocityX, this.velocityY);
    }
}