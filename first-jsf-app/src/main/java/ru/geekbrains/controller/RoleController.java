package ru.geekbrains.controller;

import ru.geekbrains.service.RoleRepr;
import ru.geekbrains.service.RoleService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class RoleController implements Serializable {

    @EJB
    private RoleService roleService;

    private RoleRepr role;

    public List<RoleRepr> getAllRoles() {
        return roleService.getAllRoles();
    }

    public RoleRepr getRole() {
        return role;
    }

    public void setRole(RoleRepr role) {
        this.role = role;
    }

}
