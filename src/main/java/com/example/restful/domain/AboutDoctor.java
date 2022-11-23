package com.example.restful.domain;

import com.example.restful.Enum.Status;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "doc_info")
public class AboutDoctor {
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String info;
    private Status status;

    public AboutDoctor() {
    }

    public AboutDoctor(String info, Status status) {
        this.info = info;
        this.status = status;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof AboutDoctor))
            return false;
        AboutDoctor aboutDoctor = (AboutDoctor) o;
        return Objects.equals(this.id, aboutDoctor.id) && Objects.equals(this.info, aboutDoctor.info)
                && this.status == aboutDoctor.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getInfo(), getStatus());
    }

    @Override
    public String toString() {
        return "AboutDoctor{" +
                "id=" + id +
                ", info='" + info + '\'' +
                ", salary=" + status +
                '}';
    }
}
