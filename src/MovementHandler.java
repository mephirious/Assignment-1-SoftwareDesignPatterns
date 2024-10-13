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

            // zombie moves
            entity.setPositionX(newX);

            if (isOutOfBounds(newX, entity, gameBoard)) {
                gameBoard.removeEntity(entity);

                // game over if zombie reached the end of map
                gameBoard.setGameOver();
            } else {
                handleZombiePlantInteraction(newX, entity, gameBoard);
            }
        } else if (nextHandler != null) {
            nextHandler.handleRequest(gameBoard, entity);
        }
    }

    private boolean entityNeedsToMove(EntityActions entity) {
        return entity.getVelocityX() > 0;
    }

    private boolean isOutOfBounds(int x, EntityActions entity, GameBoard gameBoard) {
        // zombie reached the end
        return x < 0;
    }

    // interactions between zombie and plant at the row
    private void handleZombiePlantInteraction(int x, EntityActions zombie, GameBoard gameBoard) {
        for (EntityActions plant : gameBoard.getEntities()) {
            if (plant instanceof Plant && plant.getPositionX() == x) {
                // reached the plant
                plant.changeHealth(-zombie.getDamage());

                // remove the plant
                if (plant.getHealth() <= 0) {
                    gameBoard.removeEntity(plant);
                }

                return;
            }
        }
    }
}
