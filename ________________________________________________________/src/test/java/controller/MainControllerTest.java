package controller;

import com.d1l.controller.MainController;
import com.opensymphony.xwork2.Action;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class MainControllerTest {
    private MainController controller;

    @Before
    public void setUp() throws Exception {
        controller = new MainController();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void execute() throws Exception {
        assertEquals(Action.SUCCESS, controller.execute());
    }

    @Test
    public void about() throws Exception {
        assertEquals(Action.SUCCESS, controller.about());
    }
}