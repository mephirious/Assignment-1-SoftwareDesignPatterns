abstract class GameMode {
    public final void play() {
        initialize();
        while (!isGameOver()) {
            handleInput();
            updateGameState();
            render();
        }
        endGame();
    }

    protected abstract void initialize();
    protected abstract void handleInput();
    protected abstract void updateGameState();
    protected abstract void render();
    protected abstract boolean isGameOver();
    protected abstract void endGame();
}
