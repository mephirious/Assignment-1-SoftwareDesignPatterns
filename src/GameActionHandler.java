public interface GameActionHandler {
    void setNext(GameActionHandler handler);
    void handleRequest(GameBoard gameBoard, EntityActions entity);
}
