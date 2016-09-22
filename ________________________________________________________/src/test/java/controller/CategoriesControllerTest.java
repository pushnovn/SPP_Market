package controller;

import com.d1l.controller.adminpanel.CategoriesController;
import com.d1l.model.Category;
import com.opensymphony.xwork2.Action;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class CategoriesControllerTest {
    private CategoriesController controller;
    private Category category;

    private int id = 2;
    private String name = "bla";

    @Before
    public void setUp() throws Exception {

        category = new Category();
        category.setName(name);

        controller = new CategoriesController();
        controller.setCategory(category);
        controller.setId(id);
    }

    @After
    public void tearDown() throws Exception {
        controller.delete();
    }

    @Test
    public void execute() throws Exception {
        assertEquals(Action.SUCCESS,controller.execute());
    }

    @Test
    public void add() throws Exception {
        assertEquals(Action.SUCCESS,controller.add());
    }

    @Test
    public void update() throws Exception {
        assertEquals(Action.SUCCESS,controller.update());
    }

    @Test
    public void delete() throws Exception {
        assertEquals(Action.SUCCESS,controller.delete());
    }
}