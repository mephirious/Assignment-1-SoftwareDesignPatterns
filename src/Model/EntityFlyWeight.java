package Model;

import java.awt.*;
import java.util.HashMap;

public class EntityFlyWeight {
    private static final HashMap<String, Entity> entityMap = new HashMap<>();

    public static Entity getEntityAction(String name) {
        Entity entityAction = entityMap.get(name);
        if (entityAction == null) {
            entityMap.put(name, entityAction);
        }
        return entityAction;
    }
}
