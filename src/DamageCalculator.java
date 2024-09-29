interface IDamageCalculator {
    double calculateDamage(double damageTaken, double maxDamageReductionPercentage);
}
class DamageCalculator implements IDamageCalculator{
    final private PlayerStats playerType;

    public DamageCalculator(PlayerStats playerType) {this.playerType = playerType;}

    public double calculateDamage(double damageTaken, double maxDamageReductionPercentage) {
        return damageTaken * (1 - (Math.min(maxDamageReductionPercentage, this.playerType.getArmor()))/100);
    }
}