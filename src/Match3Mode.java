class Match3Mode extends GameMode {
    private int movesLeft;
    private int score;
    private final int maxMoves = 20;

    protected void initialize() {
        movesLeft = maxMoves;
        score = 0;
    }

    protected void handleInput() {
        movesLeft--;
        score += 100;
    }

    protected void updateGameState() {
        System.out.println("Moves left: " + movesLeft);
        System.out.println("Current score: " + score);
    }

    protected void render() {
        System.out.println("Displaying match-3 game field...");
    }

    protected boolean isGameOver() {
        return movesLeft <= 0;
    }

    protected void endGame() {
        System.out.println("Match-3 Mode completed. Your score: " + score);
    }
}
