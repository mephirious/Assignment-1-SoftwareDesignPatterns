package Model;

public abstract class EntityDecorator implements GameEntity {
    protected GameEntity decoratedEntity;

    public EntityDecorator(GameEntity decoratedEntity) {
        this.decoratedEntity = decoratedEntity;
    }

    @Override
    public void show() {
        decoratedEntity.show();
    }

    @Override
    public void performAttack() {
        decoratedEntity.performAttack();
    }

    @Override
    public void performDefense() {
        decoratedEntity.performDefense();
    }
}

class FireDecorator extends EntityDecorator {
    public FireDecorator(GameEntity decoratedEntity) {
        super(decoratedEntity);
    }

    @Override
    public void performAttack() {
        super.performAttack(); // Call the original attack
        System.out.println("Adding fire damage!");
    }
}

class ArmorDecorator extends EntityDecorator {
    public ArmorDecorator(GameEntity decoratedEntity) {
        super(decoratedEntity);
    }

    @Override
    public void performDefense() {
        super.performDefense(); // Call the original defense
        System.out.println("Adding armor defense!");
    }
}

