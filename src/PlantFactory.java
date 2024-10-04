class Mushroom {
    public Mushroom(){};
}

class Peas {
    public Peas(){};
}

class AttackingPeas extends Peas {
    public void grow() {
        System.out.println("Attackin peas is growing!");
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
    public abstract Mushroom createMushroom();
    public abstract Peas createPeas();
}

class AttackingFactory extends PlantFactory {
    public Mushroom createMushroom() {
        return new AttackingMushroom();
    }

    public Peas createPeas() {
        return new AttackingPeas();
    }
}

class ProducingFactory extends PlantFactory {
    public Mushroom createMushroom() {
        return new ProducingMushroom();
    }

    public Peas createPeas() {
        return new ProducingPeas();
    }
}
