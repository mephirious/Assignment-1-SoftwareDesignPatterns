package Model;

public abstract class Container {
    private int positionX;
    private int positionY;
    private int width;
    private int height;
    private int velocityX;
    private int velocityY;
    private int directionX;
    private int directionY;

    public Container(int positionX, int positionY, int width, int height) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }
    public int getPositionX() {
        return positionX;
    }
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
    public int getPositionY() { return positionY; }

    public void setWidth(int width) { this.width = width; }
    public int getWidth() { return width; }
    public void setHeight(int height) { this.height = height; }
    public int getHeight() { return height; }

    public void setVelocityX(int velX) { this.velocityX = velX; }
    public void setVelocityY(int velY) { this.velocityY = velY; }
    public int getVelocityX() { return velocityX; }
    public int getVelocityY() { return velocityY; }

    public void setDirectionX(int dirX) { this.directionX = dirX; }
    public void setDirectionY(int dirY) { this.directionY = dirY; }
    public int getDirectionX() { return directionX; }
    public int getDirectionY() { return directionY; }


    public void updatePosition() {
        int newPositionX = getPositionX() + getVelocityX() * getDirectionX();
        int newPositionY = getPositionY() + getVelocityY() * getDirectionY();
        setPositionX(newPositionX);
        setPositionY(newPositionY);
    }
}
