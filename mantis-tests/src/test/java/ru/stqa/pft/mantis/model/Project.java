package ru.stqa.pft.mantis.model;

/**
 * Created by user on 17.04.2017.
 */
public class Project {
    public  int id;
    public String name;
    private Project status;

    public Project getStatus() {
        return status;
    }

    public Project withStatus(Project status) {
        this.status = status;
        return this;
    }

    public int getId() {
        return id;
    }

    public Project withId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Project withName(String name) {
        this.name = name;
        return this;
    }
}
