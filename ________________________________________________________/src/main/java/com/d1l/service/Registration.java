
package com.d1l.service;

import com.d1l.dao.CustomerDao;
import com.d1l.dao.RoleDao;
import com.d1l.dao.SupplierDao;
import com.d1l.dao.UserDao;
import com.d1l.model.Customer;
import com.d1l.model.Supplier;
import com.d1l.model.User;
import com.d1l.util.CryptService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//package com.d1l.service;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;
import java.util.ArrayList;
import java.util.HashMap;

public class Registration extends ActionSupport implements SessionAware
{
    private String login;
    private String password;
    private String repeatpass;
    private String firstname;
    private String middlename;
    private String lastname;
    private String companyName;
    private String message;
 // private String errorMessageForAllTypesOfUsers = "errorMessageForAllTypesOfUsers";
 // private String errorMessageForCostumer;
    private ArrayList<String> arrayListOfErrorMessagesForCostumer = new ArrayList<String>();
    private ArrayList<String> arrayListOfErrorMessagesForSupplier = new ArrayList<String>();

    public SessionMap<String, Object> getSession() {
        return session;
    }
    public void setSession(SessionMap<String, Object> session) {
        this.session = session;
    }
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

    public String getRepeatpass() {
        return repeatpass;
    }
    public void setRepeatpass(String repeatpass) {
        this.repeatpass = repeatpass;
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
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    private SessionMap<String, Object> session;
    public void setSession(Map<String, Object> map) {
        this.session = (SessionMap<String, Object>) map;
    }
    @Override
    public String execute() throws Exception {
        return Action.SUCCESS;
    }

//    public String getLogin() {
//        return login;
//    }
//
//    public void setLogin(String login) {
//        this.login = login;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getFirstname() {
//        return firstname;
//    }
//
//    public void setFirstname(String firstname) {
//        this.firstname = firstname;
//    }
//
//    public String getMiddlename() {
//        return middlename;
//    }
//
//    public void setMiddlename(String middlename) {
//        this.middlename = middlename;
//    }
//
//    public String getLastname() {
//        return lastname;
//    }
//
//    public void setLastname(String lastname) {
//        this.lastname = lastname;
//    }
//
//    public String getCompanyName() {
//        return companyName;
//    }
//
//    public void setCompanyName(String companyName) {
//        this.companyName = companyName;
//    }
//
//    public String getMessage() {
//        return errorMessageForAllTypesOfUsers;
//    }
//
//    public void setMessage(String message) {
//        this.errorMessageForAllTypesOfUsers = message;
//    }
//
//    private SessionMap<String, Object> session;
//
//    public void setSession(Map<String, Object> map) {
//        this.session = (SessionMap<String, Object>) map;
//    }
//
//    @Override
//    public String execute() throws Exception {
//        return Action.SUCCESS;
//    }

    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

//    public String singupAsCustomer() throws  Exception {
//
//        if (!validateCustomer(getLogin(), getPassword(), getFirstname(),
//                getLastname(), getMiddlename())) return Action.ERROR;
//
//        if (UserDao.getUserByLogin(this.login) != null) {
//            message = "User with this login already exist";
//            return Action.ERROR;
//        }
//
//        User user = new User();
//        Customer customer = new Customer();
//
//        user.setLogin(this.login);
//        user.setPassword(HashService.makeSHA1Hash(this.password));
//        user.setRole(RoleDao.getRoleByName("Customer"));
//        UserDao.addOrUpdateUser(user);
//
//        customer.setFirstname(this.firstname);
//        customer.setLastname(this.lastname);
//        customer.setMiddlename(this.middlename);
//        customer.setUser(UserDao.getUserByLogin(this.login));
//        CustomerDao.addOrUpdateCustomer(customer);
//
//        //auto-login after registration new account
//        session.put("id", customer.getUser().getId());
//        session.put("login", user.getLogin());
//        session.put("role", user.getRole().getName());
//
//        return Action.SUCCESS;
//    }




    // КЛИЕНТ
    public String singupAsCustomer() throws  Exception
    {
        try
        {
            // TODO: Поубирать выводы сообщений в лог
            if (!validateCustomer(
                    getLogin(), getPassword(), getRepeatpass(), getFirstname(),getLastname(), getMiddlename()
                ))
            {
                setMessageOnJSP("arrayListOfErrorMessagesForCostumer", arrayListOfErrorMessagesForCostumer);
                return Action.ERROR;
            }

            System.out.println("Fields are correct (id = 1)");

            if (UserDao.getUserByLogin(this.login) != null)
            {
                arrayListOfErrorMessagesForCostumer.add("User with this login already exists.");
                setMessageOnJSP("arrayListOfErrorMessagesForCostumer", arrayListOfErrorMessagesForCostumer);
                return Action.ERROR;
            }

            System.out.println("User is uniq (id = 2)");

            User user = new User();
            Customer customer = new Customer();

            System.out.println("User is created (id = 3)");

            user.setLogin(this.login);
            user.setPassword(new CryptService().Encrypt(this.password));
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
            return Action.ERROR;
        }
    }

    private boolean validateCustomer(String login, String password, String repeatpass, String firstname,
                                     String lastname, String middlename)
    {
        boolean correctness = true;

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
        m = passwordPattern.matcher(repeatpass);
        if (!m.matches())
        {
            arrayListOfErrorMessagesForCostumer.add("The repeat of your password is invalid.");
            correctness = false;
        }
        if (!password.equals(repeatpass))
        {
            arrayListOfErrorMessagesForCostumer.add("The passwords do not match.");
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



//    public String singupAsSupplier() throws  Exception {
//
//        if (!validateSupplier(getLogin(), getPassword(), getCompanyName())) return Action.ERROR;
//
//        if (UserDao.getUserByLogin(this.login) != null) {
//            message = "User with this login already exist";
//            return Action.ERROR;
//        }
//
//        User user = new User();
//        Supplier supplier = new Supplier();
//
//        user.setLogin(this.login);
//        user.setPassword(HashService.makeSHA1Hash(this.password));
//        user.setRole(RoleDao.getRoleByName("Supplier"));
//        UserDao.addOrUpdateUser(user);
//
//        supplier.setCompanyName(this.companyName);
//        supplier.setUser(UserDao.getUserByLogin(this.login));
//        SupplierDao.addOrUpdateSupplier(supplier);
//
//        //auto-login after registration new account
//        session.put("id", supplier.getUser().getId());
//        session.put("login", user.getLogin());
//        session.put("role", user.getRole().getName());
//
//        return Action.SUCCESS;
//    }



    // ПРОДАВЕЦ
    public String singupAsSupplier() throws  Exception
    {
        this.login = this.login.toLowerCase();

        if (!validateSupplier(getLogin(), getPassword(), getCompanyName()))
            return Action.ERROR;

        if (UserDao.getUserByLogin(this.login) != null)
        {
            arrayListOfErrorMessagesForSupplier.add("User with this login already exist");
            return Action.ERROR;
        }

        User user = new User();
        Supplier supplier = new Supplier();

        user.setLogin(this.login);
        user.setPassword(new CryptService().Encrypt(this.password));
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
        m = passwordPattern.matcher(repeatpass);
        if (!m.matches())
        {
            arrayListOfErrorMessagesForSupplier.add("The repeat of your password is invalid.");
            correctness = false;
        }
        if (password != repeatpass)
        {
            arrayListOfErrorMessagesForSupplier.add("The passwords do not match.");
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




    private void setMessageOnJSP(String key, Object value)
    {
        ValueStack stack = ActionContext.getContext().getValueStack();
        Map<String, Object> context = new HashMap<String, Object>();

        context.put(key, value);
        stack.push(context);
    }



//    private boolean validateSupplier(String login, String password, String companyName) {
//        message = "";
//        boolean error = false;
//
//        Pattern loginPattern = Pattern.compile("^[A-Za-z0-9_-]{1,30}$");
//        Matcher m = loginPattern.matcher(login);
//        if (!m.matches())
//        {
//            message += "The login is invalid; ";
//            error = true;
//        }
//        Pattern passwordPattern = Pattern.compile("^[A-Za-z0-9@#$%*]{8,60}$");
//        m = passwordPattern.matcher(password);
//        if (!m.matches())
//        {
//            message += "The password is invalid; ";
//            error = true;
//        }
//        if (!password.equals(repeatpass)) {
//            message += "Passwords not equals; ";
//            error = true;
//        }
//        Pattern companyNamePattern = Pattern.compile("^[A-Za-z0-9\\s]{1,60}$");
//        m = companyNamePattern.matcher(companyName);
//        if (!m.matches())
//        {
//            message += "The company name is invalid; ";
//            error = true;
//        }
//        if (error) return false;
//        return true;
//    }
//
//    private boolean validateCustomer(String login, String password, String firstname,
//                                     String lastname, String middlename) {
//        message = "";
//        boolean error = false;
//
//        Pattern loginPattern = Pattern.compile("^[A-Za-z0-9_-]{1,30}$");
//        Matcher m = loginPattern.matcher(login);
//        if (!m.matches())
//        {
//            message += "The login is invalid; ";
//            error = true;
//        }
//        Pattern passwordPattern = Pattern.compile("^[A-Za-z0-9@#$%*]{8,60}$");
//        m = passwordPattern.matcher(password);
//        if (!m.matches())
//        {
//            message += "The password is invalid; ";
//            error = true;
//        }
//        if (!password.equals(repeatpass)) {
//            message += "Passwords not equals; ";
//            error = true;
//        }
//        Pattern namePattern = Pattern.compile("^[A-Za-z\\s]{1,60}$");
//        m = namePattern.matcher(firstname);
//        if (!m.matches())
//        {
//            message += "The firstname is invalid; ";
//            error = true;
//        }
//        m = namePattern.matcher(lastname);
//        if (!m.matches())
//        {
//            message += "The lastname is invalid; ";
//            error = true;
//        }
//        Pattern midnamePattern = Pattern.compile("^[A-Za-z\\s]{0,60}$");
//        m = midnamePattern.matcher(getFirstname());
//        if (!m.matches())
//        {
//            message += "The middlename is invalid; ";
//            error = true;
//        }
//        if (error) return false;
//        return true;
//    }
}

