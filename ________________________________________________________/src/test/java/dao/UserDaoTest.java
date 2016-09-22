package dao;

import com.d1l.dao.RoleDao;
import com.d1l.dao.UserDao;
import com.d1l.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class UserDaoTest {

    User user;

    @Before
    public void setUp() throws Exception {

        user = new User();
        user.setRole(RoleDao.getRoleByName("Admin"));
        user.setLogin("asdasdsa");
        user.setPassword("sdadssadas");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void deleteUser() throws Exception {
        UserDao.deleteUser(2);
    }

    @Test
    public void updateUser() throws Exception {
        user.setId(2);
        UserDao.addOrUpdateUser(user);
    }

    @Test
    public void addUser() throws Exception {
        UserDao.addOrUpdateUser(user);
    }

    @Test
    public void getUserTest() throws Exception {
        assertNotNull(UserDao.getUsersList());
    }
}