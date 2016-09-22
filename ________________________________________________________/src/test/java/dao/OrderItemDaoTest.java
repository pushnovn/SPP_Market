package dao;

import com.d1l.dao.OrderItemDao;
import com.d1l.model.OrderItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class OrderItemDaoTest {

    OrderItem orderItem;

    @Before
    public void setUp() throws Exception {

        orderItem = new OrderItem();
        orderItem.setItemId(1);
        orderItem.setOrderId(1);
        orderItem.setCount(2);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void deleteOrderItem() throws Exception {
        OrderItemDao.deleteOrderItem(1);
    }

    @Test
    public void updateOrderItem() throws Exception {
        orderItem.setId(2);
        OrderItemDao.addOrUpdateOrderItem(orderItem);
    }

    @Test
    public void addOrderItem() throws Exception {
        OrderItemDao.addOrUpdateOrderItem(orderItem);
    }

    @Test
    public void getOrderItemTest() throws Exception {
        assertNotNull(OrderItemDao.getOrderItemsList());
    }
}