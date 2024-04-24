package ru.il.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "departments", schema = "uchet", catalog = "")
public class Department {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "dep_name")
    private String depName;
    @Basic
    @Column(name = "building")
    private String building;
    @Basic
    @Column(name = "room")
    private String room;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id == that.id && Objects.equals(depName, that.depName) && Objects.equals(building, that.building) && Objects.equals(room, that.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, depName, building, room);
    }
}
