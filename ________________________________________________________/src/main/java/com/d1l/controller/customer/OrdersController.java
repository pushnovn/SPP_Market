package com.d1l.controller.customer;

import com.d1l.dao.*;
import com.d1l.model.*;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrdersController extends ActionSupport {

    private List<Order> ordersList;
    private List<OrderItem> orderItems;
    private List<OrderReport> orderReports;

    @Override
    public String execute() throws Exception
    {
        try
        {
            Map session = ActionContext.getContext().getSession();
            if(session.containsKey("id"))
            {
                ordersList = OrderDao.getOrdersListByCustomer(
                        CustomerDao.getCustomerByUser(
                                UserDao.getUserById(Integer.parseInt(session.get("id").toString()))));

                orderReports = new ArrayList<OrderReport>();
                int amount = 0;
                for (Order order : ordersList)
                {
                    orderItems = OrderItemDao.getOrderItemsByOrderId(order.getId());

                    List<ItemReport> itemReports = new ArrayList<ItemReport>();
                    for (OrderItem orderItem : orderItems) {
                        Item item = ItemDao.getItemById(orderItem.getItemId());

                        ItemReport itemReport = new ItemReport();
                        itemReport.setItem(item);
                        itemReport.setCount(orderItem.getCount());
                        itemReports.add(itemReport);

                        amount += item.getPrice()*itemReport.getCount();
                    }

                    OrderReport orderReport = new OrderReport();
                    orderReport.setCustomer(CustomerDao.getCustomerById(order.getCustomer().getId()));
                    orderReport.setItemsList(itemReports);
                    orderReport.setAmount(amount);
                    orderReport.setId(order.getId());
                    orderReports.add(orderReport);
                }
            }
            return Action.SUCCESS;
        }
        catch (Exception exp)
        {
            return Action.SUCCESS;
        }
    }

    public List<OrderReport> getOrderReports() {
        return orderReports;
    }

    public void setOrderReports(List<OrderReport> orderReports) {
        this.orderReports = orderReports;
    }

    public List<Order> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Order> ordersList) {
        this.ordersList = ordersList;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
