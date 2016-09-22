package dao;

import com.d1l.dao.CategoryDao;
import com.d1l.dao.ItemDao;
import com.d1l.dao.SupplierDao;
import com.d1l.dao.MarketDao;
import com.d1l.model.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ItemDaoTest {

    Item item;

    @Before
    public void setUp() throws Exception {

        item = new Item();
        item.setName("sdad");
        item.setSupplier(SupplierDao.getSupplierById(1));
        item.setCountInMarket(20);
        item.setCategory(CategoryDao.getCategoryById(1));
        item.setId(2);
        item.setMarket(MarketDao.getMarketById(1));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void deleteItem() throws Exception {
        ItemDao.deleteItem(2);
    }

    @Test
    public void updateItem() throws Exception {
        item.setId(2);
        ItemDao.addOrUpdateItem(item);
    }

    @Test
    public void addItem() throws Exception {
        ItemDao.addOrUpdateItem(item);
    }

    @Test
    public void getItemTest() throws Exception {
        assertNotNull(ItemDao.getItemsList());
    }
}