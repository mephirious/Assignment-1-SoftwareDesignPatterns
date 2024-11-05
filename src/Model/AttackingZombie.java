package Model;

public class AttackingZombie {
    private int x, y;
    private int health;
    private int speed = 1; // Adjust for desired zombie speed

    public AttackingZombie(int x, int y) {
        this.x = x;
        this.y = y;
        this.health = 100; // Set initial health for zombies
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getHealth() { return health; }

    public void moveTo(int targetX, int targetY) {
        // Simple movement logic toward the target plant
        if (x < targetX) x += speed;
        else if (x > targetX) x -= speed;

        if (y < targetY) y += speed;
        else if (y > targetY) y -= speed;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
    }

    public boolean isAlive() {
        return health > 0;
    }
}
