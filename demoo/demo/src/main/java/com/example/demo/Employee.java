package com.example.demo;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
class Employee {

    private @Id @GeneratedValue Long id;
    private String setter;
    private String getter;
    private String message;

    Employee() {}

    Employee(String setter, String getter, String message) {

        this.setter = setter;
        this.getter = getter;
        this.message = message;
    }

    public Long getId() {
        return this.id;
    }

    public String getSetter() {
        return this.setter;
    }

    public String getGetter() {
        return this.getter;
    }

    public String getMessage() {
        return this.message;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSetter(String setter) {
        this.setter = setter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Employee))
            return false;
        Employee employee = (Employee) o;
        return Objects.equals(this.id, employee.id)
                && Objects.equals(this.setter, employee.setter)
                && Objects.equals(this.getter, employee.getter)
                && Objects.equals(this.message, employee.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.setter, this.getter, this.message);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", sender='" + this.setter + '\'' + ", victim='" + this.getter + '\'' +"message='"+ this.message + '\'' + '}';
    }
}