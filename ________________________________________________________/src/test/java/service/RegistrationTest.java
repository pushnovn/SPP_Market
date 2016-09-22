package service;


import com.d1l.model.Customer;
import com.d1l.model.User;
import com.opensymphony.xwork2.Action;
import com.d1l.service.Registration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class RegistrationTest {

    private Registration reg;
    private Customer client;
    private int id = 1;
    private int userid = 1;
    private String firstname = "bla";
    private String secondname = "bla";
    private String lastname = "bla";

    private String login = "bla";
    private String password = "blafffff";

    @Before
    public void setUp() throws Exception {
        reg = new Registration();

        reg.setFirstname(firstname);
        reg.setMiddlename(secondname);
        reg.setLastname(lastname);
        reg.setLogin(login);
        reg.setPassword(password);
        reg.setCompanyName("dsadsa");

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void execute() throws Exception {
        assertEquals(Action.SUCCESS,reg.execute());
    }

}