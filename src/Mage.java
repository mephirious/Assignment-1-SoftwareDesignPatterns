interface MageActions {
    void invisible();
}

public class Mage extends Player implements MageActions {
    private double mana;
    private final PlayerStatsManager statsManager;

    public Mage (String nickname, double maxHealth, double armor, double baseDamage, double mana) {
        super(nickname, maxHealth, armor, baseDamage);
        this.mana = mana;

        this.statsManager = new PlayerStatsManager(new DamageCalculator(this));
    }

    public void setMana(double mana) {
        this.mana = mana;
    }

    public double getMana() {
        return mana;
    }

    @Override
    public void attack() {
        if (this.getMana() >= 10) {
            mana -= 10;
            System.out.println(getNickname() + " casts a spell! Remaining mana: " + mana);
        } else {
            System.out.println(getNickname() + " does not have enough mana to attack.");
        }
    }
    @Override
    public void takeDamage(double damageTaken) {
        statsManager.applyDamage(this, damageTaken, 40);
    }

    @Override
    public void invisible() {
        if (mana >= 50) {
            mana -= 50;
            System.out.println(getNickname() + " casts a invisibility spell! Remaining mana: " + mana);
        } else {
            System.out.println(getNickname() + " does not have enough mana to cast.");
        }
    }

    @Override
    public void displayStats() {
        super.displayStats();
        System.out.printf(" Mana: %s - Mage\n", mana);
    }
}