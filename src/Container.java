public abstract class Container {
    private int positionX;
    private int positionY;

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }
    public int getPositionX() {
        return positionX;
    }
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
    public int getPositionY() {
        return positionY;
    }

    public Container(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }
}
