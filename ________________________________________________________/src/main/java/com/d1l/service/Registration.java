package com.d1l.service;

import com.d1l.dao.CustomerDao;
import com.d1l.dao.RoleDao;
import com.d1l.dao.SupplierDao;
import com.d1l.dao.UserDao;
import com.d1l.model.Customer;
import com.d1l.model.Supplier;
import com.d1l.model.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration extends ActionSupport implements SessionAware {

    private String login;
    private String password;
    private String firstname;
    private String middlename;
    private String lastname;
    private String companyName;
    private String errorMessageForAllTypesOfUsers = "errorMessageForAllTypesOfUsers";
    private String errorMessageForCostumer;
    private ArrayList<String> arrayListOfErrorMessagesForCostumer = new ArrayList<String>();
    private ArrayList<String> arrayListOfErrorMessagesForSupplier = new ArrayList<String>();


    private String errorMessageForSupplier = "ooooooooooooooooooooo";

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMessage() {
        return errorMessageForAllTypesOfUsers;
    }

    public void setMessage(String message) {
        this.errorMessageForAllTypesOfUsers = message;
    }

    private SessionMap<String, Object> session;

    public void setSession(Map<String, Object> map) {
        this.session = (SessionMap<String, Object>) map;
    }

    @Override
    public String execute() throws Exception {
        return Action.SUCCESS;
    }


    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public String singupAsCustomer() throws  Exception
    {
        try
        {
            if (!validateCustomer(
                    getLogin(), getPassword(), getFirstname(),getLastname(), getMiddlename()
                ))
            {
                setMessageOnJSP("arrayListOfErrorMessagesForCostumer", arrayListOfErrorMessagesForCostumer);
                return Action.ERROR;
            }

            System.out.println("Fields are correct (id = 1)");

            if (UserDao.getUserByLogin(this.login) != null)
            {
                setMessageOnJSP("errorMessageForCostumer","User with this login already exists.");
                return Action.ERROR;
            }

            System.out.println("User is uniq (id = 2)");

            User user = new User();
            Customer customer = new Customer();

            System.out.println("User is created (id = 3)");

            user.setLogin(this.login);
            user.setPassword(this.password);
            user.setRole(RoleDao.getRoleByName("Customer"));
            UserDao.addOrUpdateUser(user);

            System.out.println("User is in DB (id = 4");

            customer.setFirstname(this.firstname);
            customer.setLastname(this.lastname);
            customer.setMiddlename(this.middlename);
            customer.setUser(UserDao.getUserByLogin(this.login));
            CustomerDao.addOrUpdateCustomer(customer);


            System.out.println("Costumer is in DB (id = 5");

            //auto-login after registration new account
            session.put("id", customer.getUser().getId());
            session.put("login", user.getLogin());
            session.put("role", user.getRole().getName());

            System.out.println("Session is done (id = 6");

            return Action.SUCCESS;
        }
        catch (Exception excep)
        {
            System.out.println("Exception! (id = 7");

            arrayListOfErrorMessagesForCostumer.add("Something go wrong... Please, try one more time.");
            setMessageOnJSP("arrayListOfErrorMessagesForCostumer", arrayListOfErrorMessagesForCostumer);
           // setMessageOnJSP("errorMessageForCostumer","Something go wrong... Please, try one more time.");
            return Action.ERROR;
        }
    }

    private void setMessageOnJSP(String key, Object value)
    {
        ValueStack stack = ActionContext.getContext().getValueStack();
        Map<String, Object> context = new HashMap<String, Object>();

        context.put(key, value);
        stack.push(context);
    }


    public String singupAsSupplier() throws  Exception
    {

        if (!validateSupplier(getLogin(), getPassword(), getCompanyName())) return Action.ERROR;

        if (UserDao.getUserByLogin(this.login) != null)
        {
            errorMessageForSupplier = "User with this login already exist";
            return Action.ERROR;
        }

        User user = new User();
        Supplier supplier = new Supplier();

        user.setLogin(this.login);
        user.setPassword(this.password);
        user.setRole(RoleDao.getRoleByName("Supplier"));
        UserDao.addOrUpdateUser(user);

        supplier.setCompanyName(this.companyName);
        supplier.setUser(UserDao.getUserByLogin(this.login));
        SupplierDao.addOrUpdateSupplier(supplier);

        //auto-login after registration new account
        session.put("id", supplier.getUser().getId());
        session.put("login", user.getLogin());
        session.put("role", user.getRole().getName());

        return Action.SUCCESS;
    }

    private boolean validateSupplier(String login, String password, String companyName)
    {
        boolean correctness = true;

        Pattern loginPattern = Pattern.compile("^[A-Za-z0-9_-]{1,30}$");
        Matcher m = loginPattern.matcher(login);
        if (!m.matches())
        {
            arrayListOfErrorMessagesForSupplier.add("The login is invalid.");
            correctness = false;
        }
        Pattern passwordPattern = Pattern.compile("^[A-Za-z0-9@#$%*]{8,60}$");
        m = passwordPattern.matcher(password);
        if (!m.matches())
        {
            arrayListOfErrorMessagesForSupplier.add("The password is invalid.");
            correctness = false;
        }
        Pattern companyNamePattern = Pattern.compile("^[A-Za-z0-9-\\s]{1,60}$");
        m = companyNamePattern.matcher(companyName);
        if (!m.matches())
        {
            arrayListOfErrorMessagesForSupplier.add("The company name is invalid.");
            correctness = false;
        }

        return correctness;
    }

    private boolean validateCustomer(String login, String password, String firstname,
                                     String lastname, String middlename)
    {
        boolean correctness = true;
        errorMessageForCostumer = "";

        Pattern loginPattern = Pattern.compile("^[A-Za-z0-9_-]{1,30}$");
        Matcher m = loginPattern.matcher(login);
        if (!m.matches())
        {
            arrayListOfErrorMessagesForCostumer.add("The login is invalid.");
            correctness = false;
        }


        Pattern passwordPattern = Pattern.compile("^[A-Za-z0-9@#$%*]{8,60}$");
        m = passwordPattern.matcher(password);
        if (!m.matches())
        {
            arrayListOfErrorMessagesForCostumer.add("The password is invalid.");
            correctness = false;
        }


        Pattern namePattern = Pattern.compile("^[A-Za-z\\s]{1,60}$");

        m = namePattern.matcher(firstname);
        if (!m.matches())
        {
            arrayListOfErrorMessagesForCostumer.add("The firstname is invalid.");
            correctness = false;
        }
        m = namePattern.matcher(lastname);
        if (!m.matches())
        {
            arrayListOfErrorMessagesForCostumer.add("The lastname is invalid.");
            correctness = false;
        }
        m = namePattern.matcher(middlename);
        if (!m.matches())
        {
            arrayListOfErrorMessagesForCostumer.add("The middlename is invalid.");
            correctness = false;
        }


        return correctness;
    }

}

