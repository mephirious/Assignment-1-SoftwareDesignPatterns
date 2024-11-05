package Model;

interface ICooldown {
    boolean isReady();
    void trigger();
    void reset();
}

public class Cooldown implements ICooldown {
    // In milliseconds
    private final long cooldownDuration;
    // The time when the last action was performed
    private long lastActionTime;

    public Cooldown(double averageActionSpeed) {
        // Convert to milliseconds
        this.cooldownDuration = (long) (1000 * averageActionSpeed);
        // Start without cooldown
        this.lastActionTime = 0;
    }

    // Check if the cooldown has expired
    public boolean isReady() {
        long currentTime = System.currentTimeMillis();
        return (currentTime - lastActionTime) >= cooldownDuration;
    }

    // Trigger the action and reset the cooldown
    public void trigger() {
        lastActionTime = System.currentTimeMillis();
    }

    // Optional: If you want to reset the cooldown manually
    public void reset() {
        lastActionTime = 0;
    }
}
