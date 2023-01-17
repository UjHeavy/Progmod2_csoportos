package com.example.demo;

import jakarta.persistence.*;
import java.util.Objects;

@jakarta.persistence.Entity
@Table(name = "testdb")
class Entity {

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


    Entity() {}

    Entity(String setter, String getter, String message, MessageStatusEnum status) {

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
        if (!(o instanceof Entity))
            return false;
        Entity entity = (Entity) o;
        return Objects.equals(this.id, entity.id)
                && Objects.equals(this.setter, entity.setter)
                && Objects.equals(this.getter, entity.getter)
                && Objects.equals(this.message, entity.message)
                && Objects.equals(this.status, entity.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.setter, this.getter, this.message, this.status);
    }

    @Override
    public String toString() {
        return "Entity{" + "id=" + this.id + ", sender='" + this.setter + '\'' + ", victim='" + this.getter + '\'' +"message='"+ this.message + '\'' + "status=" + this.status + '}';
    }
}