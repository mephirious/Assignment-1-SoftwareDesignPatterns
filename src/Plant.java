interface IBuilderPlants {
    Plant.Builder setPositionX(int positionX);
    Plant.Builder setPositionY(int positionY);
    Plant.Builder setName(String name);
    Plant.Builder setDescription(String description);
    Plant.Builder setHealth(int health);
    Plant.Builder setDamage(int damage);
    Plant build();
}

public class Plant extends EntityActions {
    private double averageActionSpeed;
    private Cooldown cooldown;

    public Plant(int positionX, int positionY, String name, String description, int health, int damage, Projectile projectile, double averageActionSpeed) {
        super(positionX, positionY, name, description, health, damage, projectile);
        cooldown = new Cooldown(averageActionSpeed);
    }

    @Override
    public void update(GameBoard gameBoard) {
        if (cooldown.isReady()) {
            // Execute the attack logic (e.g., shoot a projectile)
            // TODO: Make some changes so it only shoots if triggered.
            System.out.println(this.getName() + " is shooting!");

            gameBoard.addProjectile(this.attack());

            // Trigger cooldown
            cooldown.trigger();
        } else {
            // On cooldown, cannot attack yet
            // System.out.println("Attack is on cooldown!");
        }
    }

    public static class Builder implements IBuilderPlants {
        private int positionX;
        private int positionY;
        private String name;
        private String description;
        private int health;
        private int damage;
        private Projectile projectile;
        private double averageActionSpeed;
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
        public Builder setAverageActionSpeed(double averageActionSpeed) {
            this.averageActionSpeed = averageActionSpeed;
            return this;
        }
        public Plant build() {
            return new Plant(positionX, positionY, name, description, health, damage, projectile, averageActionSpeed);
        }
    }

    public static class Director {
        public static Plant constructPeaShooter() {
            Projectile peaProjectile = new Projectile(0, 0, 1, 0, 5, 5);
            return (new Builder()
                    .setName("Pea Shooter")
                    .setDescription("Peashooters are your first line of defense. They shoot peas at attacking zombies.")
                    .setHealth(300)
                    .setDamage(20)
                    .setAverageActionSpeed(1.425)
                    .setProjectile(peaProjectile)
                    .build());
        }

        public static Plant constructSunflower() {
            Projectile peaProjectile = new Projectile(0, 0, 0, 0, 0, 0);
            return (new Builder()
                    .setName("Sunflower")
                    .setDescription("Sunflowers are essential for you to produce extra sun. Try planting as many as you can!")
                    .setHealth(300)
                    .setDamage(0)
                    .setAverageActionSpeed(24.25)
                    .setProjectile(peaProjectile)
                    .build());
        }

        public static Plant constructWallNut() {
            Projectile peaProjectile = new Projectile(0, 0, 0, 0, 0, 0);
            return (new Builder()
                    .setName("Wall-nut")
                    .setDescription("Wall-nuts have hard shells which you can use to protect your other plants.")
                    .setHealth(4000)
                    .setDamage(0)
                    .setAverageActionSpeed(0)
                    .setProjectile(peaProjectile)
                    .build());
        }
    }   
}