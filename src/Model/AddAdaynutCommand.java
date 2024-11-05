package Model;

public class AddAdaynutCommand implements Command {
    @Override
    public void execute(GameBoard gameBoard, int x, int y) {
        Plant wallnut = Plant.Director.constructAdayNut();
        wallnut.setPositionX(x);
        wallnut.setPositionY(y);
        wallnut.getProjectile().setPositionX(x);
        wallnut.getProjectile().setPositionY(y);
        if (gameBoard.addEntity(wallnut)) {
            System.out.println("Cannot create " + wallnut.getName() + " at " + x + ":" +y);
        }
    }
}
