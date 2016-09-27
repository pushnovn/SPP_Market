package com.d1l.controller.adminpanel;

import com.d1l.dao.RoleDao;
import com.d1l.dao.UserDao;
import com.d1l.model.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsersController extends ActionSupport {

    private List<User> usersList;
    private int id;

    public ArrayList getArrayListOfErrorMessages() {
        return arrayListOfErrorMessages;
    }

    public void setArrayListOfErrorMessages(ArrayList arrayListOfErrorMessages) {
        this.arrayListOfErrorMessages = arrayListOfErrorMessages;
    }

    public ArrayList arrayListOfErrorMessages = new ArrayList();

    @Override
    public String execute() throws Exception {
        usersList = UserDao.getUsersList();
        return Action.SUCCESS;
    }

    public String delete()
    {
        try
        {
            if (UserDao.getUserById(getId()).getLogin().equals("admin"))
                return Action.SUCCESS;

            UserDao.deleteUser(getId());

            return Action.SUCCESS;
        }
        catch (Exception exp)
        {
            SetErrorOnThePage("User isn't exists.");
            return Action.SUCCESS;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
    }



    private void SetErrorOnThePage (String errorText)
    {
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
