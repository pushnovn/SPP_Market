package com.d1l.controller.adminpanel;

import com.d1l.dao.CustomerDao;
import com.d1l.dao.ItemDao;
import com.d1l.dao.OrderDao;
import com.d1l.dao.OrderItemDao;
import com.d1l.model.*;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;

public class OrdersController extends ActionSupport {

    private List<Order> ordersList;
    private List<OrderItem> orderItems;
    private List<OrderReport> orderReports;

    @Override
    public String execute() throws Exception
    {
        try
        {
            ordersList = OrderDao.getOrdersList();

            orderReports = new ArrayList<OrderReport>();
            for (Order order : ordersList) {
                orderItems = OrderItemDao.getOrderItemsByOrderId(order.getId());

                int amount = 0;
                List<ItemReport> detailReports = new ArrayList<ItemReport>();
                for (OrderItem orderItem : orderItems) {
                    Item item = ItemDao.getItemById(orderItem.getItemId());

                    ItemReport itemReport = new ItemReport();
                    itemReport.setItem(item);
                    itemReport.setCount(orderItem.getCount());
                    detailReports.add(itemReport);

                    amount += item.getPrice() * orderItem.getCount();
                }

                OrderReport orderReport = new OrderReport();
                orderReport.setCustomer(CustomerDao.getCustomerById(order.getCustomer().getId()));
                orderReport.setItemsList(detailReports);
                orderReport.setAmount(amount);
                orderReport.setId(order.getId());
                orderReports.add(orderReport);
            }
        }
        catch (Exception exp) {}

        return Action.SUCCESS;
    }

    public List<OrderReport> getOrderReports() {
        return orderReports;
    }

    public void setOrderReports(List<OrderReport> orderReports) {
        this.orderReports = orderReports;
    }

}
