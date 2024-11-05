package Model;

public class AddNursnutCommand implements Command {
    @Override
    public void execute(GameBoard gameBoard, int x, int y) {
        ZombieVerse wallnut = ZombieVerse.Director.constructNursNut();
        wallnut.setPositionX(x);
        wallnut.setPositionY(y);
        wallnut.getProjectile().setPositionX(x);
        wallnut.getProjectile().setPositionY(y);
        if (gameBoard.addEntity(wallnut)) {
            System.out.println("Cannot create " + wallnut.getName() + " at " + x + ":" +y);
        }
    }
}
