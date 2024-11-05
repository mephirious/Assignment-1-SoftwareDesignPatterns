package Model;

import java.util.List;
import java.util.ArrayList;

public class ZombieClone implements Cloneable {
    protected int row;
    private int health;
    private List<Observer> observers = new ArrayList<>();

    public ZombieClone(int health) {
        this.health = health;
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.updateHealth(this.getClass().getSimpleName(), this.getHealth());
        }
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void changeHealth(int amount) {
        this.health += amount;
        notifyObservers();
    }

    public int getHealth() {
        return this.health;
    }

    public void spawn() {
        System.out.println("Model.Zombie spawned at row: " + row);
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    protected ZombieClone clone() throws CloneNotSupportedException {
        return (ZombieClone) super.clone();
    }
}

class NormalZombie extends ZombieClone {
    public NormalZombie(int health) {
        super(health);
    }

    @Override
    public void spawn() {
        System.out.println("Normal Model.Zombie spawned at row: " + row);
    }
}

class StrongZombie extends ZombieClone {
    public StrongZombie(int health) {
        super(health);
    }

    @Override
    public void spawn() {
        System.out.println("Strong Model.Zombie spawned at row: " + row);
    }
}

class BossZombie extends ZombieClone {
    public BossZombie(int health) {
        super(health);
    }

    @Override
    public void spawn() {
        System.out.println("Boss Model.Zombie spawned at row: " + row);
    }
}
