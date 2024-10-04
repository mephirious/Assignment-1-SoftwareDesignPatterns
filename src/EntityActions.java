interface Actions {
    Projectile attack();
    void update(GameBoard gameBoard);
}

class Entity extends Container {
    private String name;
    private String description;
    private int health;
    private int damage;

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

public abstract class EntityActions extends Entity {
    private Projectile projectile;

    public EntityActions(int positionX, int positionY, String name, String description, int health, int damage, Projectile projectile) {
        super(positionX, positionY, name, description, health, damage, projectile);
        this.projectile = projectile;
    }

    public void setProjectile(Projectile projectile) { this.projectile = projectile; }
    public Projectile getProjectile() { return projectile; }

    public Projectile attack() { return projectile.clone(); }
    public void update(GameBoard gameBoard) { System.out.println("Update entity: " + getName()); }
}

