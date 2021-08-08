package models;

public class Hero {
    private int id;
    private int squadId;

    private String name;
    private int age;
    private String strength;
    private String weakness;

    // Constructor function
    public Hero(
            String name,
            int age,
            String strength,
            String weakness,
            int squadId
    ) {
        this.name = name;
        this.age = age;
        this.strength = strength;
        this.weakness = weakness;
        this.squadId = squadId;
    }

    // Getters and setters

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getWeakness() {
        return weakness;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }

    // Overrides for object class add and hash()

}
