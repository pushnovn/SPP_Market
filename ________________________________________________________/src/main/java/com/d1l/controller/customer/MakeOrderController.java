package com.d1l.controller.customer;

import com.d1l.dao.*;
import com.d1l.model.*;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MakeOrderController extends ActionSupport {

    private List<ItemReport> itemsList;

    public List<ItemReport> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<ItemReport> itemsList) {
        this.itemsList = itemsList;
    }

    @Override
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();

        List<ItemInfoForOrder> items;

        if (session.containsKey("itemsForOrder")) {
            items = (List<ItemInfoForOrder>)session.get("itemsForOrder");
        } else {
            return Action.SUCCESS;
        }

        itemsList = new ArrayList<ItemReport>();
        for (ItemInfoForOrder itemInfo : items) {
            ItemReport itemReport = new ItemReport();
            itemReport.setItem(ItemDao.getItemById(itemInfo.getItemId()));
            itemReport.setCount(itemInfo.getCount());
            itemsList.add(itemReport);
        }

        return Action.SUCCESS;
    }

    public String makeOrder() throws Exception {
        Map session = ActionContext.getContext().getSession();

        List<ItemInfoForOrder> items;

        if (session.containsKey("itemsForOrder")) {
            items = (List<ItemInfoForOrder>)session.get("itemsForOrder");
        } else {
            return Action.SUCCESS;
        }

        Order order = new Order();
        Customer customer = CustomerDao.getCustomerByUser(
                UserDao.getUserById(Integer.parseInt(session.get("id").toString())));
        order.setCustomer(customer);
        order.setDate(new Date());
        OrderDao.addOrUpdateOrder(order);
        order = OrderDao.getOrderByCustomerAndLastDate(customer);

        for (ItemInfoForOrder itemInfo : items) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order.getId());
            orderItem.setCount(itemInfo.getCount());
            orderItem.setItemId(itemInfo.getItemId());
            Item d = ItemDao.getItemById(itemInfo.getItemId());
            int c = d.getCountInMarket();
            d.setCountInMarket(c > 0 ? c-- : c);
            ItemDao.addOrUpdateItem(d);
            OrderItemDao.addOrUpdateOrderItem(orderItem);
        }

        session.remove("itemsForOrder");

        return Action.SUCCESS;
    }

}
