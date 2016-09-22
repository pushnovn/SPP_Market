package dao;

import com.d1l.dao.SupplierDao;
import com.d1l.dao.UserDao;
import com.d1l.model.Supplier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SupplierDaoTest {

    Supplier supplier;

    @Before
    public void setUp() throws Exception {

        supplier = new Supplier();
        supplier.setCompanyName("dsadasdas");
        supplier.setUser(UserDao.getUserById(2));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void deleteSupplier() throws Exception {
        SupplierDao.deleteSupplier(1);
    }

    @Test
    public void updateSupplier() throws Exception {
        supplier.setId(2);
        SupplierDao.addOrUpdateSupplier(supplier);
    }

    @Test
    public void addSupplier() throws Exception {
        SupplierDao.addOrUpdateSupplier(supplier);
    }

    @Test
    public void getSupplierTest() throws Exception {
        assertNotNull(SupplierDao.getSuppliersList());
    }
}