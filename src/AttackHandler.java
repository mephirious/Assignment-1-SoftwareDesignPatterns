public class AttackHandler implements GameActionHandler {
    private GameActionHandler nextHandler;

    @Override
    public void setNext(GameActionHandler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handleRequest(GameBoard gameBoard, EntityActions entity) {
        // check if the entity can attack
        if (entity.getBehavior() instanceof AttackBehavior) {
            AttackBehavior attackBehavior = (AttackBehavior) entity.getBehavior();
            EntityActions target = findTarget(gameBoard, entity);

            if (target != null) {
                attackBehavior.performAction(entity, gameBoard);
                target.changeHealth(-entity.getDamage());

                // remove the target if health = 0
                if (target.getHealth() <= 0) {
                    gameBoard.removeEntity(target);
                }
            }
        } else if (nextHandler != null) {
            nextHandler.handleRequest(gameBoard, entity);
        }
    }

    // finding the target to attack
    private EntityActions findTarget(GameBoard gameBoard, EntityActions entity) {

        if (entity instanceof Zombie) {
            // zombie attacks
            for (EntityActions plant : gameBoard.getEntities()) {
                if (plant instanceof Plant && plant.getPositionX() == entity.getPositionX()) {
                    return plant;
                }
            }
        } else if (entity instanceof Plant) {
            // plant attacks
            for (EntityActions zombie : gameBoard.getEntities()) {
                if (zombie instanceof Zombie && zombie.getPositionX() == entity.getPositionX()) {
                    return zombie;
                }
            }
        }

        // target is not found
        return null;
    }
}
