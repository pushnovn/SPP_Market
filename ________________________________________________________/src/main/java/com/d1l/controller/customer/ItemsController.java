package com.d1l.controller.customer;

import com.d1l.dao.ItemDao;
import com.d1l.model.Item;
import com.d1l.model.ItemInfoForOrder;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemsController extends ActionSupport {

    private List<Item> itemsList;
    private int id;

    private int itemId;
    private int count;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String execute() throws Exception {
        itemsList = ItemDao.getItemsList();
        return Action.SUCCESS;
    }

    public String addItemForOrder() throws Exception {
        Map session = ActionContext.getContext().getSession();

        List<ItemInfoForOrder> items;

        if (session.containsKey("itemsForOrder")) {
            items = (List<ItemInfoForOrder>)session.get("itemsForOrder");
        } else {
            items = new ArrayList<ItemInfoForOrder>();
        }

        ItemInfoForOrder itemInfo = new ItemInfoForOrder();
        itemInfo.setItemId(itemId);
        itemInfo.setCount(count);
        items.add(itemInfo);
        session.put("itemsForOrder", items);

        return Action.SUCCESS;
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
}
