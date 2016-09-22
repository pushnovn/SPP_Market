package dao;

import com.d1l.dao.MarketDao;
import com.d1l.model.Market;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MarketDaoTest {

    Market warehouse;

    @Before
    public void setUp() throws Exception {

        warehouse = new Market();
        warehouse.setName("sdad");
        warehouse.setAddress("dsadsadas, 2");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void deleteMarket() throws Exception {
        MarketDao.deleteMarket(1);
    }

    @Test
    public void updateMarket() throws Exception {
        warehouse.setId(2);
        MarketDao.addOrUpdateMarket(warehouse);
    }

    @Test
    public void addMarket() throws Exception {
        MarketDao.addOrUpdateMarket(warehouse);
    }

    @Test
    public void getMarketTest() throws Exception {
        assertNotNull(MarketDao.getMarketsList());
    }
}