    import java.util.ArrayList;
    import java.util.List;

    public interface ILevelFactory {
        Level createLevel();
    }

    class DayLevelFactory implements ILevelFactory {
        public Level createLevel() {
            List<Integer> spawnRows = new ArrayList<>();
            spawnRows.add(1);
            spawnRows.add(3);
            spawnRows.add(1);
            spawnRows.add(2);
            return new Level(spawnRows, "Day", "Normal");
        }
    }

    class NightLevelFactory implements ILevelFactory {
        public Level createLevel() {
            List<Integer> spawnRows = new ArrayList<>();
            spawnRows.add(1);
            spawnRows.add(2);
            spawnRows.add(1);
            spawnRows.add(3);
            return new Level(spawnRows, "Night", "Dark");
        }
    }

    class BossLevelFactory implements ILevelFactory {
        public Level createLevel() {
            List<Integer> spawnRows = new ArrayList<>();
            spawnRows.add(2);
            spawnRows.add(1);
            spawnRows.add(3);
            spawnRows.add(2);
            return new Level(spawnRows, "Day", "Boss Terrain");
        }
    }
