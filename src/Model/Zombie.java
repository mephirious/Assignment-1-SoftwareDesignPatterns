package Model;

import java.util.List;
import java.util.ArrayList;

public class Zombie implements Cloneable {
    protected int row;
    private int health;
    private List<Observer> observers = new ArrayList<>();

    public Zombie(int health) {
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
    protected Zombie clone() throws CloneNotSupportedException {
        return (Zombie) super.clone();
    }
}

class NormalZombie extends Zombie {
    public NormalZombie(int health) {
        super(health);
    }

    @Override
    public void spawn() {
        System.out.println("Normal Model.Zombie spawned at row: " + row);
    }
}

class StrongZombie extends Zombie {
    public StrongZombie(int health) {
        super(health);
    }

    @Override
    public void spawn() {
        System.out.println("Strong Model.Zombie spawned at row: " + row);
    }
}

class BossZombie extends Zombie {
    public BossZombie(int health) {
        super(health);
    }

    @Override
    public void spawn() {
        System.out.println("Boss Model.Zombie spawned at row: " + row);
    }
}
