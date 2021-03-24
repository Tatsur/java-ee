package ru.geekbrains.controller;

import ru.geekbrains.service.UserRepr;
import ru.geekbrains.service.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class UserController implements Serializable {

    @EJB
    private UserService userService;

    private UserRepr user;

    public UserRepr getUser() {
        return user;
    }

    public void setUser(UserRepr user) {
        this.user = user;
    }

    public String createUser() {
        this.user = new UserRepr();
        return "/admin/user_form.xhtml?faces-redirect=true";
    }

    public List<UserRepr> getAllUsers() {
        return userService.getAllUsers();
    }

    public String editUser(UserRepr user) {
        this.user = user;
        return "/admin/user_form.xhtml?faces-redirect=true";
    }

    public void deleteUser(UserRepr user) {
        userService.delete(user.getId());
    }

    public String saveUser() {
        userService.saveOrUpdate(user);
        return "/admin/user.xhtml?faces-redirect=true";
    }
}
