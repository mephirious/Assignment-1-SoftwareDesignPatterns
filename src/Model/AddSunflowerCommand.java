package Model;

public class AddSunflowerCommand implements Command {
    @Override
    public void execute(GameBoard gameBoard, int x, int y) {
        Plant sunflower = Plant.Director.constructSunflower();
        sunflower.setPositionX(x);
        sunflower.setPositionY(y);
        sunflower.getProjectile().setPositionX(x);
        sunflower.getProjectile().setPositionY(y);
        if (gameBoard.addEntity(sunflower)) {
            System.out.println("Cannot create " + sunflower.getName() + " at " + x + ":" +y);
        }
    }
}
