import java.util.ArrayList;

public abstract class Storage implements Actions {
    protected ArrayList<Person> peopleList;

    public Storage() {
        peopleList = new ArrayList<>();
    }
}


