public class Person {
    private int id;
    private String fullName;
    private int age;

    Person(int id, String fullName, int age) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
    }

    public void setId(int id) {this.id = id;}
    public int getId() {return this.id;}
    public void setFullName(String fullName) {this.fullName = fullName;}
    public String getFullName() {return this.fullName;}
    public void setAge(int age) {this.age = age;}
    public int getAge() {return this.age;}

    public String showInfo() {
        return "ID: " + id + ", Full Name: " + fullName + ", Age: " + age;
    }
}


