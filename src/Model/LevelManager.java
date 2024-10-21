public class LevelManager {
    private static LevelManager instance;
    private Level currentLevel;

    private LevelManager() {}

    public static LevelManager getInstance() {
        if (instance == null) {
            instance = new LevelManager();
        }
        return instance;
    }

    public void setCurrentLevel(Level level) {
        this.currentLevel = level;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }
}
