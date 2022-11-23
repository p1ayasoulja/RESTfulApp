package com.example.restful.domain;

import com.example.restful.Enum.Salary;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "doc_info")
public class AboutDoctor {
    private @Id
    @GeneratedValue(strategy = GenerationType.TABLE) Long id;
    private String info;
    private Salary salary;

    public AboutDoctor() {
    }

    public AboutDoctor(String info, Salary salary) {
        this.info = info;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof AboutDoctor))
            return false;
        AboutDoctor aboutDoctor = (AboutDoctor) o;
        return Objects.equals(this.id, aboutDoctor.id) && Objects.equals(this.info, aboutDoctor.info)
                && this.salary == aboutDoctor.salary;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getInfo(), getSalary());
    }

    @Override
    public String toString() {
        return "AboutDoctor{" +
                "id=" + id +
                ", info='" + info + '\'' +
                ", salary=" + salary +
                '}';
    }
}
