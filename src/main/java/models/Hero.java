package models;

import java.util.Objects;

public class Hero {
    private int id;
    private int squadId;

    private String name;
    private int age;
    private String power;
    private String weakness;

    // Constructor function
    public Hero(
            String name,
            int age,
            String power,
            String weakness,
            int squadId
    ) {
        this.name = name;
        this.age = age;
        this.power = power;
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

    public int getSquadId() {
        return squadId;
    }

    public void setSquadId(int squadId) {
        this.squadId = squadId;
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

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getWeakness() {
        return weakness;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }

    // Overrides for object class add and hash()

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Hero)) return false;
        Hero hero = (Hero) obj;
        return id == hero.id &&
                Objects.equals(name, hero.name) &&
                Objects.equals(age,hero.age) &&
                Objects.equals(power, hero.power) &&
                Objects.equals(weakness, hero.weakness) &&
                Objects.equals(squadId, hero.squadId) ;
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, id,power,weakness,squadId);
    }


}
