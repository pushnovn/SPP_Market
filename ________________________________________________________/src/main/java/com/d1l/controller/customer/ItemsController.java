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

    private String itemId;
    private String count;

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getItemId() {
        return itemId;
    }

    public String getCount() {
        return count;
    }

    @Override
    public String execute() throws Exception {
        itemsList = ItemDao.getItemsList();
        return Action.SUCCESS;
    }

    public String addItemForOrder() throws Exception {
        Map session = ActionContext.getContext().getSession();
        try {
            if (ItemDao.getItemById(Integer.parseInt(itemId)) == null) return Action.SUCCESS;
            Integer.parseInt(count);
        } catch (Exception ex) {return  Action.SUCCESS;}

        List<ItemInfoForOrder> items;

        if (session.containsKey("itemsForOrder")) {
            items = (List<ItemInfoForOrder>)session.get("itemsForOrder");
        } else {
            items = new ArrayList<ItemInfoForOrder>();
        }

        ItemInfoForOrder itemInfo = new ItemInfoForOrder();
        itemInfo.setItemId(Integer.parseInt(itemId));
        itemInfo.setCount(Integer.parseInt(count));
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

}
