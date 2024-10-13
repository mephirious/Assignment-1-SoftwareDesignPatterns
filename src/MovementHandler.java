public class MovementHandler implements GameActionHandler {
    private GameActionHandler nextHandler;

    @Override
    public void setNext(GameActionHandler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handleRequest(GameBoard gameBoard, EntityActions entity) {
        if (entity instanceof Zombie && entityNeedsToMove(entity)) {
            int currentX = entity.getPositionX();

            int velocityX = entity.getVelocityX();

            int newX = currentX - velocityX;

            // reset the pos of zombie
            entity.setPositionX(newX);

            // if zombie reached the bound of plant
            if (isOutOfBounds(newX, entity, gameBoard)) {
                gameBoard.removeEntity(entity);
                gameBoard.setGameOver();
            }

        } else if (nextHandler != null) {
            nextHandler.handleRequest(gameBoard, entity);
        }
    }

    private boolean entityNeedsToMove(EntityActions entity) {
        return entity.getVelocityX() > 0;
    }

    private boolean isOutOfBounds(int x, EntityActions entity, GameBoard gameBoard) {
        if (x < 0) {
            return true;
        }

        for (EntityActions plant : gameBoard.getEntities()) {
            if (plant instanceof Plant && plant.getPositionX() == x) {
                // zombie reached the plant
                plant.changeHealth(-entity.getDamage());

                // zombie continues to attack
                return false;
            }
        }
        return false;
    }
}
