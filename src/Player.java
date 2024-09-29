interface PlayerActions {
    void attack();
    void takeDamage(double dmg);
}

interface PlayerStats {
    double getHealth();
    double getArmor();
    double getDamage();
    void setHealth(double health);
    boolean isDead();
}

interface PlayerDisplay {
    void displayStats();
}

class PlayerStatsManager {
    private final IDamageCalculator damageCalculator;

    public PlayerStatsManager(IDamageCalculator damageCalculator) {
        this.damageCalculator = damageCalculator;
    }

    public void applyDamage(PlayerStats playerType, double damageTaken, double maxDamageReductionPercentage) {
        playerType.setHealth(playerType.getHealth() - damageCalculator.calculateDamage(damageTaken, maxDamageReductionPercentage));
    }
}

interface CanBlock {
    void block(double damage);
}

abstract class Player implements PlayerActions, PlayerStats, PlayerDisplay {
    private final String nickname;
    private double health;
    private double armor;
    private double damage;

    public Player(String nickname, double health, double armor, double damage) {
        this.nickname = nickname;
        this.health = health;
        this.armor = armor;
        this.damage = damage;
    }
    public String getNickname() {
        return nickname;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getArmor() {
        return armor;
    }

    public void setArmor(double armor) {
        this.armor = armor;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public boolean isDead() {
        return this.health <= 0;
    }

    @Override
    public abstract void attack();

    @Override
    public abstract void takeDamage(double damageTaken);

    @Override
    public void displayStats() {
        System.out.printf("%s: %shp | Armor: %s | Damage: %s", nickname, (int) health, (int) armor, (int) damage);
    }
}