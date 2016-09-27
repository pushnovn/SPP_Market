package com.d1l.controller.adminpanel;

import com.d1l.dao.RoleDao;
import com.d1l.dao.UserDao;
import com.d1l.model.User;
import com.d1l.util.CryptService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddAdminController extends ActionSupport
{
    private User user;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String execute() throws Exception {
        return Action.SUCCESS;
    }

    private ArrayList<String> arrayListOfErrorMessages = new ArrayList<String>();
    private String errorMessage = "Something go wrong... Try one more time please.";

    public String repeatpassword;
    public String getRepeatpassword() {
        return repeatpassword;
    }
    public void setRepeatpassword(String repeatpassword) {
        this.repeatpassword = repeatpassword;
    }

    // ++ АДМИН Admin
    public String add()
    {
        try
        {
            if (!validateAdmin(
                    getUser().getLogin(), getUser().getPassword(), repeatpassword, getUser().getEmail()
            ))
            {
                setMessageOnJSP("arrayListOfErrorMessages", arrayListOfErrorMessages);
                return Action.ERROR;
            }

            if (UserDao.getUserByLogin(getUser().getLogin()) != null)
            {
                SetErrorOnThePage("User with this name is already exist");
                return Action.ERROR;
            }

            user.setRole(RoleDao.getRoleByName("Admin"));
            String encryptPass = new CryptService().Encrypt(user.getPassword());
            user.setPassword(encryptPass);
            UserDao.addOrUpdateUser(getUser());

            return Action.SUCCESS;
        }
        catch (Exception ex)
        {
            SetErrorOnThePage(errorMessage);
            return Action.ERROR;
        }
    }
    // -- АДМИН Admin

    // ++ АДМИН Admin
    private boolean validateAdmin(String login, String password, String repeatpass, String email)
    {
        try
        {
            boolean correctness = true;

            Pattern loginPattern = Pattern.compile("^[A-Za-z0-9_-]{1,30}$");
            Matcher m = loginPattern.matcher(login);
            if (!m.matches())
            {
                arrayListOfErrorMessages.add("The login is invalid.");
                correctness = false;
            }
            Pattern passwordPattern = Pattern.compile("^[A-Za-z0-9@#$%*]{8,60}$");
            m = passwordPattern.matcher(password);
            if (!m.matches())
            {
                arrayListOfErrorMessages.add("The password is invalid.");
                correctness = false;
            }
            m = passwordPattern.matcher(repeatpass);
            if (!m.matches())
            {
                arrayListOfErrorMessages.add("The repeat of your password is invalid.");
                correctness = false;
            }
            if (!password.equals(repeatpass))
            {
                arrayListOfErrorMessages.add("The passwords do not match.");
                correctness = false;
            }

            Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
            m = emailPattern.matcher(email);
            if (!m.matches())
            {
                arrayListOfErrorMessages.add("The email is invalid.");
                correctness = false;
            }

            return correctness;
        }
        catch (Exception exp)
        {
            arrayListOfErrorMessages.add(errorMessage);
            return false;
        }
    }
    // -- АДМИН Admin


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
