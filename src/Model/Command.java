package Model;

public interface Command {
    void execute(GameBoard gameBoard, int x, int y);
}
