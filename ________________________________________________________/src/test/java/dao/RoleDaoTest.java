package dao;

import com.d1l.dao.RoleDao;
import com.d1l.model.Role;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class RoleDaoTest {
    private Role role;
    private int id = 4;
    private String name = "bla";

    @Before
    public void setUp() throws Exception {
        role = new Role();
        role.setId(id);
        role.setName(name);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getRoleList() throws Exception {
        assertNotNull(RoleDao.getRolesList());
    }

    @Test
    public void deleteRole() throws Exception {
        RoleDao.deleteRole(id);
    }

    @Test
    public void updateRole() throws Exception {
        RoleDao.addOrUpdateRole(role);
    }

    @Test
    public void addRole() throws Exception {
        RoleDao.addOrUpdateRole(role);
    }

}