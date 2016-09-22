package com.d1l.controller.adminpanel;

import com.d1l.dao.MarketDao;
import com.d1l.model.Market;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarketsController extends ActionSupport {

    private Market market;
    private List<Market> marketsList;
    private int id;

    @Override
    public String execute() throws Exception {
        marketsList = MarketDao.getMarketsList();
        return Action.SUCCESS;
    }

    public String update() {
        if (!validate(getMarket())) return Action.SUCCESS;
        MarketDao.addOrUpdateMarket(getMarket());
        return Action.SUCCESS;
    }

    public String delete() {
        MarketDao.deleteMarket(getId());
        return Action.SUCCESS;
    }

    public String add() {
        if (!validate(getMarket())) return Action.SUCCESS;
        MarketDao.addOrUpdateMarket(getMarket());
        return Action.SUCCESS;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public List<Market> getMarketsList() {
        return marketsList;
    }

    public void setMarketsList(List<Market> marketsList) {
        this.marketsList = marketsList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String errorString;

    public String getErrorString() {
        return errorString;
    }

    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }

    private boolean validate(Market market)
    {
        Pattern namePattern = Pattern.compile("^[A-Za-z\\s]{1,100}$");
        Matcher m = namePattern.matcher(market.getName());
        if (!m.matches())
        {
            errorString = "The name is invalid";
            return false;
        }
        Pattern addressPattern = Pattern.compile("^[A-Za-z0-9,.\\s]{1,250}$");
        m = addressPattern.matcher(market.getAddress());
        if (!m.matches())
        {
            errorString = "The address is invalid";
            return false;
        }
        return true;
    }
}
