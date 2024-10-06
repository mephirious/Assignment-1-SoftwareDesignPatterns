public class ZombieFactory {
    private static Zombie normalZombiePrototype = new NormalZombie();
    private static Zombie strongZombiePrototype = new StrongZombie();
    private static Zombie bossZombiePrototype = new BossZombie();

    public static Zombie createZombie(String timeOfDay) {
        switch (timeOfDay) {
            case "Day":
                return cloneZombie(normalZombiePrototype);
            case "Night":
                return cloneZombie(strongZombiePrototype);
            case "Boss":
                return cloneZombie(bossZombiePrototype);
            default:
                return cloneZombie(normalZombiePrototype);
        }
    }

    private static Zombie cloneZombie(Zombie zombie) {
        try {
            return zombie.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
