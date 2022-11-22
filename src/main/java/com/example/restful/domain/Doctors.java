package com.example.restful.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.print.Doc;
import java.util.Objects;

@Entity

public class Doctors {
    private @Id
    @GeneratedValue(strategy = GenerationType.TABLE) Long id;
    private String firstName;
    private String lastName;
    private String position;

    public Doctors() {
    }

    public Doctors(String firstName, String lastName, String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public void setName(String name) {
        String[] parts = name.split(" ");
        this.firstName = parts[0];
        this.lastName = parts[1];
    }

    public String getPosition() {
        return position;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Doctors)) return false;
        Doctors doctors = (Doctors) o;
        return Objects.equals(id, doctors.id)
                && Objects.equals(firstName, doctors.firstName)
                && Objects.equals(lastName, doctors.lastName)
                && Objects.equals(position, doctors.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstName, this.lastName, this.position);
    }

    @Override
    public String toString() {
        return "Doctor" + "id=" + this.id + "," +
                "firstName='" + this.firstName + '\'' + ", " +
                "lastName='" + this.lastName + '\'' + ", " +
                "position='" + this.position + '\'' + '}';
    }
}
