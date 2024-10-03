public class Cooldown {
    private final long cooldownDuration; // In milliseconds
    private long lastActionTime; // The time when the last action was performed

    public Cooldown(double averageActionSpeed) {
        this.cooldownDuration = (long) (1000 * averageActionSpeed); // Convert to milliseconds
        this.lastActionTime = 0; // Start without cooldown
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
