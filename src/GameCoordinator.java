public class GameCoordinator implements GameMediator {
    private GameBoard gameBoard;

    public GameCoordinator(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public void attack(EntityActions attacker, EntityActions target) {
        if (target != null) {
            target.changeHealth(-attacker.getDamage());

            if (target.getHealth() <= 0) {
                gameBoard.removeEntity(target);
                System.out.println(target.getName() + "has been eaten.");
            }
        }
    }

    @Override
    public void move(EntityActions entity, int newX, int newY) {
        entity.setPosition(newX, newY);
        System.out.println(entity.getName() + " moved to " + newX + "and " + newY);
    }

    @Override
    public void notify(EntityActions entity, String event) {
        System.out.println(entity.getName() + " received event: " + event);
    }

    @Override
    public void spawnZombie(Zombie zombie) {
        gameBoard.addEntity(zombie);
        System.out.println("Zombie " + zombie.getName() + " has spawned on the game board.");
    }

    @Override
    public void spawnPlant(Plant plant) {
        gameBoard.addEntity(plant);
        System.out.println("Plant " + plant.getName() + " has been placed on the game board.");
    }
}
