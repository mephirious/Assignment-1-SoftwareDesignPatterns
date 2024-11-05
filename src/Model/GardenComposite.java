package Model;

import java.util.ArrayList;
import java.util.List;

interface GameEntity {
    void show();
    void performAttack();
    void performDefense();
}

public class GardenComposite implements GameEntity {
    private List<GameEntity> entities = new ArrayList<>();

    public void add(GameEntity entity) {
        entities.add(entity);
    }

    public void remove(GameEntity entity) {
        entities.remove(entity);
    }

    @Override
    public void show() {
        System.out.println("Displaying Garden:");
        for (GameEntity entity : entities) {
            entity.show();
        }
    }

    @Override
    public void performAttack() {
        for (GameEntity entity : entities) {
            entity.performAttack();
        }
    }

    @Override
    public void performDefense() {
        for (GameEntity entity : entities) {
            entity.performDefense();
        }
    }
}

