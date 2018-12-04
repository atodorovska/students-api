package com.finki.wp.studentsapi;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tmps")
public class Tmp {

    @Id
    @GeneratedValue
    private Long id;

    private String string;

    public Tmp() {
    }

    public Tmp(String string) {
        this.string = string;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return "Tmp{" +
                "id=" + id +
                ", string='" + string + '\'' +
                '}';
    }
}
