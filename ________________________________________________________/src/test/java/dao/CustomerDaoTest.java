package dao;

import com.d1l.dao.CustomerDao;
import com.d1l.model.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class CustomerDaoTest {

    Customer customer;

    @Before
    public void setUp() throws Exception {

        customer = new Customer();
        customer.setFirstname("dsadsad");
        customer.setMiddlename("dsadsa");
        customer.setLastname("dsadsa");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void deleteCustomer() throws Exception {
        CustomerDao.deleteCustomer(2);
    }

    @Test
    public void updateCustomer() throws Exception {
        customer.setId(2);
        CustomerDao.addOrUpdateCustomer(customer);
    }

    @Test
    public void addCustomer() throws Exception {
        CustomerDao.addOrUpdateCustomer(customer);
    }

    @Test
    public void getCustomerTest() throws Exception {
        assertNotNull(CustomerDao.getCustomersList());
    }
}