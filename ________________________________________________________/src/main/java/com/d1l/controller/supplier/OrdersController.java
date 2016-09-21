package com.d1l.controller.supplier;

import com.d1l.dao.CustomerDao;
import com.d1l.dao.ItemDao;
import com.d1l.dao.OrderDao;
import com.d1l.dao.OrderItemDao;
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
    public String execute() throws Exception {
        ordersList = OrderDao.getOrdersList();
        Map session = ActionContext.getContext().getSession();

        orderReports = new ArrayList<OrderReport>();
        for (Order order : ordersList) {
            orderItems = OrderItemDao.getOrderItemsByOrderId(order.getId());

            int amount = 0;
            List<ItemReport> detailReports = new ArrayList<ItemReport>();
            for (OrderItem orderItem : orderItems) {
                Item detail = ItemDao.getItemById(orderItem.getItemId());

                if (session.containsKey("id")) {
                    if (detail.getSupplier().getUser().getId() == Integer.parseInt(session.get("id").toString())) {
                        ItemReport detailReport = new ItemReport();
                        detailReport.setItem(detail);
                        detailReport.setCount(orderItem.getCount());
                        detailReports.add(detailReport);

                        amount += detail.getPrice() * orderItem.getCount();
                    }
                }
            }

            OrderReport orderReport = new OrderReport();
            orderReport.setCustomer(CustomerDao.getCustomerById(order.getCustomer().getId()));
            orderReport.setItemsList(detailReports);
            orderReport.setAmount(amount);
            orderReport.setId(order.getId());
            orderReports.add(orderReport);
        }

        return Action.SUCCESS;
    }

    public List<OrderReport> getOrderReports() {
        return orderReports;
    }

    public void setOrderReports(List<OrderReport> orderReports) {
        this.orderReports = orderReports;
    }

}
