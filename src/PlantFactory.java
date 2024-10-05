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

// Abstract Factory
public abstract class PlantFactory {
    public abstract MushroomFactory createMushroomFactory();
    public abstract PeasFactory createPeasFactory();
}

// Concrete Factory (Abstract Factory Implementation)
class AttackingPlantFactory extends PlantFactory {
    public MushroomFactory createMushroomFactory() {
        return new AttackingMushroomFactory();  // Factory Method
    }

    public PeasFactory createPeasFactory() {
        return new AttackingPeasFactory();  // Factory Method
    }
}

// Factory Method for Mushroom
abstract class MushroomFactory {
    abstract Mushroom createMushroom();
}

class AttackingMushroomFactory extends MushroomFactory {
    public Mushroom createMushroom() {
        return new AttackingMushroom();  // Specific Mushroom
    }
}

class ProducingMushroomFactory extends MushroomFactory {
    public Mushroom createMushroom() {
        return new ProducingMushroom();  // Specific Mushroom
    }
}


// Factory Method for Peas
abstract class PeasFactory {
    abstract Mushroom createPeas();
}

class AttackingPeasFactory extends PeasFactory {
    public Mushroom createPeas() {
        return new AttackingMushroom();  // Specific Mushroom
    }
}

class ProducingPeasFactory extends PeasFactory {
    public Mushroom createPeas() {
        return new ProducingMushroom();  // Specific Mushroom
    }
}

