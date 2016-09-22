package controller;

import com.d1l.controller.adminpanel.DashboardController;
import com.opensymphony.xwork2.Action;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class DashboardControllerTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void execute() throws Exception {
        assertEquals(Action.SUCCESS, new DashboardController().execute());
    }

}