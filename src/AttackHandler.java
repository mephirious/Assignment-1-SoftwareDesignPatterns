public class AttackHandler implements GameActionHandler {
    private GameActionHandler nextHandler;

    @Override
    public void setNext(GameActionHandler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handleRequest(GameBoard gameBoard, EntityActions entity) {
        if (entity.getBehavior() instanceof AttackBehavior) {
            entity.getBehavior().performAction(entity, gameBoard);
        } else if (nextHandler != null) {
            nextHandler.handleRequest(gameBoard, entity);
        }
    }
}
