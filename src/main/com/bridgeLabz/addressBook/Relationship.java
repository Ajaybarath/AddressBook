package main.com.bridgeLabz.addressBook;

public class Relationship {

    int id;
    String name;

    public Relationship(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Relationship() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
