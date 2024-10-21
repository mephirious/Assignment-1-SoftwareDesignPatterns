interface Observer {
    void updateHealth(String entityName, int health);
}

public class HealthObserver implements Observer {
    public void updateHealth(String entityName, int health) {
        System.out.println(entityName + "'s health is now: " + health);
    }
}
