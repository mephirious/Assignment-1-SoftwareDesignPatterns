package Model;

public interface State {
    void performAction(Plant plant);
}

class ActiveState implements State {
    @Override
    public void performAction(Plant plant) {
        if (plant.getHealth() > 0) {
            System.out.println(plant.getName() + " is actively attacking!");
        } else {
            plant.setState(new InactiveState());
        }
    }
}

class InactiveState implements State {
    @Override
    public void performAction(Plant plant) {
        if (plant.getHealth() <= 0) {
            System.out.println(plant.getName() + " is died!");
        } else {
            plant.setState(new ActiveState());
        }
    }
}

