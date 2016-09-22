package controller;

import com.d1l.controller.adminpanel.UsersController;
import com.d1l.model.User;
import com.opensymphony.xwork2.Action;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class UsersControllerTest {
    private UsersController controller;

    private int id = 2;

    @Before
    public void setUp() throws Exception {

        controller = new UsersController();
        controller.setId(id);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void execute() throws Exception {
        assertEquals(Action.SUCCESS,controller.execute());
    }

    @Test
    public void delete() throws Exception {
        assertEquals(Action.SUCCESS,controller.delete());
    }
}