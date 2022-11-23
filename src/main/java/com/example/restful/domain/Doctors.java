package com.example.restful.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "doctors")
public class Doctors {
    private @Id
    @GeneratedValue(strategy = GenerationType.TABLE) Long id;
    private String firstName;
    private String lastName;
    private String resume;

    public Doctors() {
    }

    public Doctors(String firstName, String lastName, String resume) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.resume = resume;
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

    public String getResume() {
        return resume;
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

    public void setResume(String position) {
        this.resume = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Doctors)) return false;
        Doctors doctors = (Doctors) o;
        return Objects.equals(id, doctors.id)
                && Objects.equals(firstName, doctors.firstName)
                && Objects.equals(lastName, doctors.lastName)
                && Objects.equals(resume, doctors.resume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstName, this.lastName, this.resume);
    }

    @Override
    public String toString() {
        return "Doctor" + "id=" + this.id + "," +
                "firstName='" + this.firstName + '\'' + ", " +
                "lastName='" + this.lastName + '\'' + ", " +
                "resume='" + this.resume + '\'' + '}';
    }
}
