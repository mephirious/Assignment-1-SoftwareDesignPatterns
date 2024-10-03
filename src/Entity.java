interface Shape {
    public double getArea();
    public boolean CheckCollision(Shape shape);
}

class Square implements Shape {
    private int width;

    @Override
    public double getArea() {
        return (double) this.width * this.width;
    }

    @Override
    public boolean CheckCollision(Shape shape) {
        return false;
    }
}

abstract class Container {
    private int positionX;
    private int positionY;
    private Shape shape;
    // private Image image;

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public Container(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }
}

public class Entity extends Container {
    private final String name;
    private final String description;
    private int health;
    private final int damage;
    private final Projectile projectile;



    public Entity(int positionX, int positionY, String name, String description, int health, int damage, Projectile projectile) {
        super(positionX, positionY);
        this.name = name;
        this.description = description;
        this.health = health;
        this.damage = damage;
        this.projectile = projectile;
    }

    public Projectile attack() {
        return projectile.clone();
    }

    public void update(GameBoard gameBoard) {
        System.out.println("Update entity " + this.name);
    }


    // Getters for properties
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getHealth() { return health; }
    public int getDamage() { return damage; }
    public Projectile getProjectile() { return projectile; }

    // Entity Builder class
    public static class Builder {
        private int positionX;
        private int positionY;
        private String name;
        private String description;
        private int health;
        private int damage;
        private Projectile projectile;

        public Builder setPositionX(int positionX) {
            this.positionX = positionX;
            return this;
        }

        public Builder setPositionY(int positionY) {
            this.positionY = positionY;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setHealth(int health) {
            this.health = health;
            return this;
        }

        public Builder setDamage(int damage) {
            this.damage = damage;
            return this;
        }

        public Builder setProjectile(Projectile projectile) {
            this.projectile = projectile;
            return this;
        }

        public Entity build() {
            return new Entity(positionX, positionY, name, description, health, damage, projectile);
        }
    }
}