package Model;

class Mushroom {
    public Mushroom(){};
}

class Peas {
    public Peas(){};
}

class AttackingPeas extends Peas {
    public void grow() {
        System.out.println("Attacking peas is growing!");
    }
}

class ProducingPeas extends Peas {
    public void grow() {
        System.out.println("Producing peas is growing!");
    }
}

class AttackingMushroom extends Mushroom {
    public void spore() {
        System.out.println("Attacking mushroom is releasing spores!");
    }
}

class ProducingMushroom extends Mushroom {
    public void spore() {
        System.out.println("Producing mushroom is releasing spores!");
    }
}

public abstract class PlantFactory {
    public abstract Peas createPlant();
    public abstract Mushroom createMushroom();
}
class AttackingFactory extends PlantFactory {
    public Peas createPlant() {
        return new AttackingPeas();
    }
    public Mushroom createMushroom() {
        return new AttackingMushroom();
    }
}
class ProducingFactory extends PlantFactory {
    public Peas createPlant() {
        return new ProducingPeas();
    }
    public Mushroom createMushroom() {
        return new ProducingMushroom();
    }
}

