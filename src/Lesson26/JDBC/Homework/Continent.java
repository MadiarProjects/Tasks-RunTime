package Lesson26.JDBC.Homework;

public class Continent {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;
    String name;

    public Continent(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
