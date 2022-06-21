package ru.minnegaliev.springapp.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class User {

    private Integer id;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String surname;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String name;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String lastName;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String jobTitle;

    public User() {
    }

    public User(String surname, String name, String lastName, String jobTitle) {
        this.surname = surname;
        this.name = name;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
