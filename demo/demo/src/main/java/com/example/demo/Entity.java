package com.example.demo;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "messages")
class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "setter")
    private String setter;

    @Column(name = "getter")
    private String getter;

    @Column(name = "message")
    private String message;

    @Column(name = "status")
    private MessageStatusEnum status;


    Employee() {}

    Employee(String setter, String getter, String message, MessageStatusEnum status) {

        this.setter = setter;
        this.getter = getter;
        this.message = message;
        this.status = status;
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

    public MessageStatusEnum getStatus() {
        return this.status;
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

    public void setStatus(MessageStatusEnum status) {
        this.status = status;
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
                && Objects.equals(this.message, employee.message)
                && Objects.equals(this.status, employee.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.setter, this.getter, this.message, this.status);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", sender='" + this.setter + '\'' + ", victim='" + this.getter + '\'' +"message='"+ this.message + '\'' + "status=" + this.status + '}';
    }
}