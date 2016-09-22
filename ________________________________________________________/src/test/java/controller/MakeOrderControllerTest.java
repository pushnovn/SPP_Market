package controller;

import com.d1l.controller.customer.MakeOrderController;
import com.opensymphony.xwork2.Action;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class MakeOrderControllerTest {

    private MakeOrderController controller;

    @Before
    public void setUp() throws Exception {
        controller = new MakeOrderController();
    }

    @After
    public void tearDown() throws Exception {

    }

}