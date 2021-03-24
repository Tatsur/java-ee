package ru.geekbrains.service;

import ru.geekbrains.persist.Role;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class RoleRepr implements Serializable {

    private long id;

    private String name;

    public RoleRepr() {
    }

    public RoleRepr(Role r) {
        this.id = r.getId();
        this.name = r.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RoleRepr roleRepr = (RoleRepr) obj;
        return name.equals(roleRepr.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
