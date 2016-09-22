package controller;

import com.d1l.controller.customer.ItemsController;
import com.opensymphony.xwork2.Action;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ItemsControllerTest {
    private ItemsController controller;

    @Before
    public void setUp() throws Exception {

        controller = new ItemsController();
        controller.setItemId(2);
        controller.setId(1);
        controller.setCount(2);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void execute() throws Exception {
        assertEquals(Action.SUCCESS,controller.execute());
    }

}