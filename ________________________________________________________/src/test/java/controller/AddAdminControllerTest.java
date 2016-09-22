package controller;

import com.d1l.controller.adminpanel.AddAdminController;
import com.d1l.dao.RoleDao;
import com.d1l.model.User;
import com.opensymphony.xwork2.Action;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class AddAdminControllerTest {
    private AddAdminController controller;
    private User user;
    private String login = "blasdd";
    private String password = "blasdsada";

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setLogin(login);
        user.setRole(RoleDao.getRoleByName("Admin"));
        user.setPassword(password);

        controller = new AddAdminController();
        controller.setUser(user);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void execute() throws Exception {
        assertEquals(Action.SUCCESS,controller.execute());
    }

    @Test
    public void add() throws Exception {
        assertEquals(Action.SUCCESS,controller.add());
    }

}