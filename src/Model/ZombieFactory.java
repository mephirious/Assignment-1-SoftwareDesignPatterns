package Model;

public class ZombieFactory {
    private static Zombie normalZombiePrototype = new NormalZombie(250);
    private static Zombie strongZombiePrototype = new StrongZombie(500);
    private static Zombie bossZombiePrototype = new BossZombie(1000);

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
