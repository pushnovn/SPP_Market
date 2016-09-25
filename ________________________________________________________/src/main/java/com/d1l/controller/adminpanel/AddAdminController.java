package com.d1l.controller.adminpanel;

import com.d1l.dao.RoleDao;
import com.d1l.dao.UserDao;
import com.d1l.model.User;
import com.d1l.util.HashService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddAdminController extends ActionSupport {

    private User user;

    @Override
    public String execute() throws Exception {
        return Action.SUCCESS;
    }

    public String add() {
        if (!validate(getUser().getLogin(), getUser().getPassword())) return Action.ERROR;
        if (UserDao.getUserByLogin(getUser().getLogin()) != null) {
            errorString = "User with this name is already exist";
            return Action.ERROR;
        }
        user.setRole(RoleDao.getRoleByName("Admin"));
        try {
            user.setPassword(HashService.makeSHA1Hash(user.getPassword()));
        } catch (Exception ex) {}
        UserDao.addOrUpdateUser(getUser());
        return Action.SUCCESS;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private String errorString;

    public String getErrorString() {
        return errorString;
    }

    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }

    private boolean validate(String login, String password) {
        Pattern loginPattern = Pattern.compile("^[A-Za-z0-9_-]{1,30}$");
        Matcher m = loginPattern.matcher(login);
        if (!m.matches())
        {
            errorString = "The login is invalid";
            return false;
        }
        Pattern passwordPattern = Pattern.compile("^[A-Za-z0-9@#$%*]{8,60}$");
        m = passwordPattern.matcher(password);
        if (!m.matches())
        {
            errorString = "The password is invalid";
            return false;
        }
        return true;
    }
}
