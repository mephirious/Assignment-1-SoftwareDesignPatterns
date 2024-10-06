public class Zombie implements Cloneable {
    protected int row;

    public void spawn() {
        System.out.println("Zombie spawned at row: " + row);
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
    @Override
    public void spawn() {
        System.out.println("Normal Zombie spawned at row: " + row);
    }
}

class StrongZombie extends Zombie {
    @Override
    public void spawn() {
        System.out.println("Strong Zombie spawned at row: " + row);
    }
}

class BossZombie extends Zombie {
    @Override
    public void spawn() {
        System.out.println("Boss Zombie spawned at row: " + row);
    }
}
