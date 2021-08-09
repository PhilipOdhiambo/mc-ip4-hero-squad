package models;

import java.util.Objects;

public class Squad {
    int id;
    int maxSize;
    String name;
    String cause;

    // Constructor function
    public Squad(
            int maxSize,
            String name,
            String cause
    ) {
        this.name = name;
        this.maxSize = maxSize;
        this.cause = cause;
    }

    // Getters and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    // Overrides


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Squad)) return false;
        Squad squad = (Squad) obj;
        return id == squad.id &&
                Objects.equals(name, squad.name) &&
                Objects.equals(maxSize,squad.maxSize) &&
                Objects.equals(cause,squad.cause);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id,maxSize,cause);
    }
}
