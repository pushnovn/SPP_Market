package com.d1l.service;

import com.d1l.dao.UserDao;
import com.d1l.model.User;
import com.d1l.util.CryptService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Authorisation extends ActionSupport implements SessionAware
{
    private ArrayList<String> arrayListOfErrorMessages = new ArrayList<String>();

    private SessionMap<String, Object> session;

    private String login;
    private String password;

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String execute() throws Exception {
        return Action.SUCCESS;
    }

    public String logout() throws Exception {
        session.invalidate();
        return Action.SUCCESS;
    }

    public String login() throws Exception
    {
        //this.login = this.login.toLowerCase();
        User user = UserDao.getUserByLogin(this.login);
        if (user == null)
        {
            SetErrorOnThePage("Please, check the username. Such user is not exists.");
            return Action.LOGIN;
        }
        else
        {
            if (!new CryptService().Decrypt(user.getPassword()).equals(this.password))
            {
                SetErrorOnThePage("Please, check the password you entered.");
                return Action.LOGIN;
            }
            else
            {
                session.put("id", user.getId());
                session.put("login", user.getLogin());
                session.put("role", user.getRole().getName());
                return Action.SUCCESS;
            }
        }
    }

//    public void
//    }

    public void setSession(Map<String, Object> map) {
        this.session = (SessionMap<String, Object>) map;
    }

    private void SetErrorOnThePage (String errorText)
    {
        System.out.println(login + ": " + errorText);
        arrayListOfErrorMessages.add(errorText);
        setMessageOnJSP("arrayListOfErrorMessages", arrayListOfErrorMessages);
    }

    private void setMessageOnJSP(String key, Object value)
    {
        ValueStack stack = ActionContext.getContext().getValueStack();
        Map<String, Object> context = new HashMap<String, Object>();

        context.put(key, value);
        stack.push(context);
    }


}

