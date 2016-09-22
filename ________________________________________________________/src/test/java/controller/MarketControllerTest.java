package controller;

import com.d1l.controller.adminpanel.MarketsController;
import com.d1l.model.Market;
import com.opensymphony.xwork2.Action;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class MarketControllerTest {
    private MarketsController controller;
    private Market market;

    private int id = 2;
    private String name = "bla";
    private String address = "dadasdadsadas 4";

    @Before
    public void setUp() throws Exception {

        market = new Market();
        market.setName(name);
        market.setAddress(address);

        controller = new MarketsController();
        controller.setMarket(market);
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