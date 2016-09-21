package com.d1l.controller.supplier;

import com.d1l.dao.*;
import com.d1l.model.Category;
import com.d1l.model.Item;
import com.d1l.model.Market;
import com.d1l.model.Supplier;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemsController extends ActionSupport {
    
    private Item item;
    private List<Item> itemsList;
    private int id;
    private List<Category> categoriesList;
    private List<Market> marketsList;

    public List<Category> getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(List<Category> categoriesList) {
        this.categoriesList = categoriesList;
    }

    public List<Market> getMarketsList() {
        return marketsList;
    }

    public void setMarketsList(List<Market> marketsList) {
        this.marketsList = marketsList;
    }

    @Override
    public String execute() throws Exception {
        itemsList = ItemDao.getItemsList();
        categoriesList = CategoryDao.getCategoriesList();
        marketsList = MarketDao.getMarketsList();
        return Action.SUCCESS;
    }

    public String update() {
        if (!validate(getItem())) return Action.SUCCESS;
        ItemDao.addOrUpdateItem(getItem());
        return Action.SUCCESS;
    }

    public String delete() {
        ItemDao.deleteItem(getId());
        return Action.SUCCESS;
    }

    public String add() {
        if (!validate(getItem())) return Action.SUCCESS;
        getItem().setCategory(CategoryDao.getCategoryById(getItem().getCategory().getId()));
        getItem().setMarket(MarketDao.getMarketById(getItem().getMarket().getId()));
        Map session = ActionContext.getContext().getSession();
        if (session.containsKey("id")) {
            Supplier supplier = SupplierDao.getSupplierByUser(
                    UserDao.getUserById(Integer.parseInt(session.get("id").toString())));
            getItem().setSupplier(supplier);
        }
        ItemDao.addOrUpdateItem(getItem());
        return Action.SUCCESS;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Item> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Item> itemsList) {
        this.itemsList = itemsList;
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

    private boolean validate(Item item)
    {
        Pattern namePattern = Pattern.compile("^[A-Za-z\\s]{1,100}$");
        Matcher m = namePattern.matcher(item.getName());
        if (!m.matches())
        {
            errorString = "The name is invalid";
            return false;
        }
        return true;
    }


}
