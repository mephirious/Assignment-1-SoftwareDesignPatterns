public class Score {

    private static Score instance;
    private int data;

    private Score(int data) {
        this.data = data;
    }

    public static Score getInstance(int data) {
        if(instance == null) {
            instance = new Score(data);
        }
        return instance;
    }

    public void addScore(int value) {
        this.data += value;
    }

    public int getScore() {
        return this.data;
    }
}