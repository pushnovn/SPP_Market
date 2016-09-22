package dao;

import com.d1l.dao.CustomerDao;
import com.d1l.dao.OrderDao;
import com.d1l.model.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertNotNull;

public class OrderDaoTest {
    private Order order;

    @Before
    public void setUp() throws Exception {
        order = new Order();
        order.setId(2);
        order.setCustomer(CustomerDao.getCustomerById(1));
        order.setDate(new Date());

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void deleteOrder() throws Exception {
        OrderDao.deleteOrder(2);
    }
    @Test
    public void updateOrder() throws Exception {
       OrderDao.addOrUpdateOrder(order);
    }

    @Test
    public void addOrder() throws Exception {
        OrderDao.addOrUpdateOrder(order);
    }

    @Test
    public void getVisitList() throws Exception {
        assertNotNull(OrderDao.getOrdersList());
    }
}