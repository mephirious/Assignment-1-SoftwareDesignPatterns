public interface GameMediator {
    void attack(EntityActions attacker, EntityActions target);
    void move(EntityActions entity, int newX, int newY);
    void notify(EntityActions entity, String event);
    void spawnZombie(Zombie zombie);
    void spawnPlant(Plant plant);
}
