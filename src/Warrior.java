interface WarriorActions {
}
public class Warrior extends Player implements CanBlock, WarriorActions{
    private final PlayerStatsManager statsManager;
    private double blockAmount;
    public Warrior(String nickname, double health, double armor, double damage, double blockAmount) {
        super(nickname, health, armor, damage);
        this.statsManager = new PlayerStatsManager(new DamageCalculator(this));
        this.blockAmount = blockAmount;
    }

    @Override
    public void attack() {
        System.out.println(super.getNickname() + " swings a sword!");
    }

    @Override
    public void takeDamage(double damageTaken) {
        statsManager.applyDamage(this, damageTaken, 80);
    }
    // Implement the block method
    public void block(double damage) {
        double reducedDamage = damage - blockAmount; // Calculate damage reduction
        if (reducedDamage < 0) {
            reducedDamage = 0; // Ensure damage doesn't go negative
        }
        this.takeDamage(reducedDamage); // Apply reduced damage using existing takeDamage method
        System.out.println(getNickname() + " blocks the attack, reducing damage by " + blockAmount + ".");
    }

    @Override
    public void displayStats() {
        super.displayStats();
        System.out.print(" - Warrior\n");
    }
}
