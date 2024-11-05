package Model;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private List<Zombie> zombies;
    private List<Integer> spawnRows;
    private String timeOfDay;
    private String terrain;

    public Level(List<Integer> spawnRows, String timeOfDay, String terrain) {
        this.spawnRows = spawnRows;
        this.timeOfDay = timeOfDay;
        this.terrain = terrain;
        this.zombies = new ArrayList<>();
        generateZombies();
    }

    private void generateZombies() {
        for (int row : spawnRows) {
            Zombie zombie = ZombieFactory.createZombie(timeOfDay);
            zombie.setRow(row);
            zombies.add(zombie);
        }
    }

    public List<Zombie> getZombies() {
        return zombies;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public String getTerrain() {
        return terrain;
    }
}
