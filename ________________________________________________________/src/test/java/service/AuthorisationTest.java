package service;

import com.d1l.dao.RoleDao;
import com.d1l.dao.UserDao;
import com.d1l.model.User;
import com.d1l.service.Authorisation;
import com.opensymphony.xwork2.Action;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class AuthorisationTest {
    private User user;
    private String login = "blasdd";
    private String password = "blasdsada";

    private Authorisation log;
    private Map<String, Object> map;

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setId(2);

        user.setRole(RoleDao.getRoleByName("Admin"));
        user.setLogin(login);
        user.setPassword(password);
        UserDao.addOrUpdateUser(user);

        log = new Authorisation();
        log.setLogin(login);
        log.setPassword(password);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void login() throws Exception {
       assertEquals(Action.SUCCESS,log.execute());

    }

    @Test
    public void logout() throws Exception {
        assertEquals(Action.SUCCESS,log.execute());
    }

}