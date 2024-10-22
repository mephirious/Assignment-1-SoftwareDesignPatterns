package Model;

interface Memento {
    long getTickNumber();
    boolean isRunning();
    boolean isPaused();
}

public class GameMemento implements Memento {
    private final long tickNumber;
    private final boolean isRunning;
    private final boolean isPaused;

    public GameMemento(long tickNumber, boolean isRunning, boolean isPaused) {
        this.tickNumber = tickNumber;
        this.isRunning = isRunning;
        this.isPaused = isPaused;
    }

    public GameMemento save() {
        return new GameMemento(tickNumber, isRunning, isPaused);
    }

    public long getTickNumber() {
        return tickNumber;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean isPaused() {
        return isPaused;
    }
}
