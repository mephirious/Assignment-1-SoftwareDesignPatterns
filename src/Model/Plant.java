package Model;

import java.util.ArrayList;
import java.util.List;

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
    private List<Observer> observers = new ArrayList<>();
    private State state;

    public Plant(int teamID, int positionX, int positionY, int width, int height, String name, String description, int health, int damage, Projectile projectile,  EntityBehavior behavior, double averageActionSpeed, int dirX, int dirY, int velX, int velY) {
        super(teamID, positionX, positionY, width, height, name, description, health, damage, projectile, behavior);
        setDirectionX(dirX);
        setDirectionY(dirY);
        setVelocityX(velX);
        setVelocityY(velY);
        cooldown = new Cooldown(averageActionSpeed);
        this.state = new InactiveState();
    }

    public void setState(State state) {this.state = state;}

    @Override
    public void update(GameBoard gameBoard) {
        if (cooldown.isReady()) {
            // Execute the attack logic (e.g., shoot a projectile)
            // TODO: Make some changes so it only shoots if triggered.
            if (this.getBehavior().performAction(this, gameBoard)) {
                // Trigger cooldown
                cooldown.trigger();
            };
        } else {
            // On cooldown, cannot attack yet
            // System.out.println("Attack is on cooldown!");
        }
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.updateHealth(this.getName(), this.getHealth());
        }
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void changeHealth(int amount) {
        this.health += amount;
        notifyObservers();
    }



    public static class Builder implements IBuilderPlants {
        private int teamID = -1;
        private int positionX;
        private int positionY;
        private int directionX;
        private int directionY;
        private int velocityX;
        private int velocityY;
        private int width;
        private int height;
        private String name = "Model.Plant name";
        private String description = "Model.Plant description";
        private int health = 0;
        private int damage = 0;
        private Projectile projectile;
        private EntityBehavior behavior = new PassiveBehavior();
        private double averageActionSpeed;
        public Builder setTeamID(int teamID) {
            this.teamID = teamID;
            return this;
        }
        public Builder setPositionX(int positionX) {
            this.positionX = positionX;
            return this;
        }
        public Builder setPositionY(int positionY) {
            this.positionY = positionY;
            return this;
        }
        public Builder setDirectionX(int directionX) {
            this.directionX = directionX;
            return this;
        }
        public Builder setDirectionY(int directionY) {
            this.directionY = directionY;
            return this;
        }
        public Builder setVelocityX(int velocityX) {
            this.velocityX = velocityX;
            return this;
        }
        public Builder setVelocityY(int velocityY) {
            this.velocityY = velocityY;
            return this;
        }
        public Builder setWidth(int width) {
            this.width = width;
            return this;
        }
        public Builder setHeight(int height) {
            this.height = height;
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
        public Builder setBehavior(EntityBehavior behavior) {
            this.behavior = behavior;
            return this;
        }
        public Builder setAverageActionSpeed(double averageActionSpeed) {
            this.averageActionSpeed = averageActionSpeed;
            return this;
        }
        public Plant build() {
            return new Plant(teamID, positionX, positionY, width, height, name, description, health, damage, projectile, behavior, averageActionSpeed, directionX, directionY, velocityX, velocityY);
        }
    }

    public static class Director {
        public static Plant constructPeaShooter() {
            Projectile peaProjectile = new Projectile(0, 0, 1, 0, 10, 10, "Pea");
            return (new Builder()
                    .setName("Pea Shooter")
                    .setDescription("Peashooters are your first line of defense. They shoot peas at attacking zombies.")
                    .setWidth(80)
                    .setHeight(80)
                    .setHealth(300)
                    .setDamage(20)
                    .setAverageActionSpeed(1.425)
                    .setTeamID(0)
                    .setProjectile(peaProjectile)
                    .setBehavior(new AttackBehavior())
                    .build());
        }

        public static Plant constructSunflower() {
            Projectile peaProjectile = new Projectile(0, 0, 0, 0, 0, 0, "Sun");
            return (new Builder()
                    .setName("Sunflower")
                    .setDescription("Sunflowers are essential for you to produce extra sun. Try planting as many as you can!")
                    .setWidth(80)
                    .setHeight(80)
                    .setHealth(300)
                    .setDamage(0)
                    .setAverageActionSpeed(24.25)
                    .setTeamID(2)
                    .setProjectile(peaProjectile)
                    .setBehavior(new ProduceSunBehavior())
                    .build());
        }

        public static Plant constructWallNut() {
            Projectile peaProjectile = new Projectile(0, 0, 0, 0, 0, 0, "");
            return (new Builder()
                    .setTeamID(0)
                    .setName("Wall-nut")
                    .setDescription("Wall-nuts have hard shells which you can use to protect your other plants.")
                    .setWidth(80)
                    .setHeight(80)
                    .setHealth(4000)
                    .setDamage(100)
                    .setAverageActionSpeed(0)
                    .setTeamID(0)
                    .setProjectile(peaProjectile)
                    .build());
        }

        public static Plant constructAdayNut() {
            Projectile peaProjectile = new Projectile(0, 0, 0, 0, 0, 0, "");
            return (new Builder()
                    .setTeamID(0)
                    .setName("Aday-nut")
                    .setDescription("Wall-nuts have hard shells which you can use to protect your other plants.")
                    .setWidth(100)
                    .setHeight(100)
                    .setHealth(200)
                    .setDamage(20)
                    .setAverageActionSpeed(1)
                    .setTeamID(1)
                    .setProjectile(peaProjectile)
                    .setDirectionX(-1)
                    .setVelocityX(5)
                    .setBehavior(new AttackAndMoveBehavior())
                    .build());
        }
    }   
}

