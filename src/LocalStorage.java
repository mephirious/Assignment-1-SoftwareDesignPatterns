public class LocalStorage extends Storage{
    public void getAll() {
        if (peopleList.isEmpty()) {
            System.out.println("Error: list is empty");
            return;
        }
        for (Person person : peopleList) {
            System.out.println(person.showInfo());
        }
    }

    public void getOne(int id) {
        if (peopleList.isEmpty()) {
            System.out.println("Error: list is empty");
            return;
        } else if (peopleList.size() < id || id < 0) {
            System.out.println("Error: invalid id");
            return;
        }
        System.out.println(peopleList.get(id).showInfo());
    }

    public void add(Person person) {
        peopleList.add(person);
    }
}


