class Battle {
    private Player player1;
    private Player player2;

    public Battle(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void start() {
        while (!player1.isDead() && !player2.isDead()) {
            player1.attack();
            player2.takeDamage(player1.getDamage());

            if (!player2.isDead()) {
                if (canBlock(player2)) {
                    ((CanBlock) player2).block(player1.getDamage());
                } else {
                    player2.attack();
                    player1.takeDamage(player2.getDamage());
                }
            }
        }
    }

    private boolean canBlock(Player player) {
        return player instanceof CanBlock && player.getHealth() < 10; // Example condition
    }

    public void printResults() {
        System.out.printf("--------\n");
        if (player1.isDead()){
            System.out.printf("%s won a battle. Huge W\n", player2.getNickname());
        } else if (player2.isDead()) {
            System.out.printf("%s won a battle. Huge W\n", player1.getNickname());
        }
        player1.displayStats();
        player2.displayStats();
    }
}
