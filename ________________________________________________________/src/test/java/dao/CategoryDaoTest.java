package dao;

import com.d1l.dao.*;
import com.d1l.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class CategoryDaoTest {

    Category category;

    @Before
    public void setUp() throws Exception {

        category = new Category();
        category.setName("sdad");
    }

    @After
    public void tearDown() throws Exception {
        
    }

    @Test
    public void deleteCategory() throws Exception {
        CategoryDao.deleteCategory(1);
    }

    @Test
    public void updateCategory() throws Exception {
        category.setId(2);
        CategoryDao.addOrUpdateCategory(category);
    }

    @Test
    public void addCategory() throws Exception {
        CategoryDao.addOrUpdateCategory(category);
    }

    @Test
    public void getCategoryTest() throws Exception {
        assertNotNull(CategoryDao.getCategoriesList());
    }
}