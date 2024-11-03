package Model;

public class AddPeashooterCommand implements Command {
    @Override
    public void execute(GameBoard gameBoard, int x, int y) {
        Plant peashooter = Plant.Director.constructPeaShooter();
        peashooter.setPositionX(x);
        peashooter.setPositionY(y);
        peashooter.getProjectile().setPositionX(x);
        peashooter.getProjectile().setPositionY(y);
        if (gameBoard.addEntity(peashooter)) {
            System.out.println("Cannot create " + peashooter.getName() + " at " + x + ":" +y);
        }
    }
}
