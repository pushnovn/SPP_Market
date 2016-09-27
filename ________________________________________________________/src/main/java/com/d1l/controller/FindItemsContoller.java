package com.d1l.controller;

import com.d1l.dao.ItemDao;
import com.d1l.model.Item;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

public class FindItemsContoller extends ActionSupport {
    private List<Item> itemsList;

    public List<Item> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Item> itemsList) {
        this.itemsList = itemsList;
    }

    @Override
    public String execute() throws Exception {
        try{
        itemsList = ItemDao.getItemsList();
        return Action.SUCCESS;} catch (Exception exp){return Action.SUCCESS;}
    }
}

