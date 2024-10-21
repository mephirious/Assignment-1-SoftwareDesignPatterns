class StoryMode extends GameMode {
    private int storyProgress;
    private final int maxStoryProgress = 10;

    protected void initialize() {
        System.out.println("Initializing Story Mode...");
        storyProgress = 0;
    }

    protected void handleInput() {
        storyProgress++;
    }

    protected void updateGameState() {
        System.out.println("Story progress: " + storyProgress + "/" + maxStoryProgress);
    }

    protected void render() {
        System.out.println("Displaying story and game field...");
    }

    protected boolean isGameOver() {
        return storyProgress >= maxStoryProgress;
    }

    protected void endGame() {
        System.out.println("Story Mode completed!");
    }
}
