package com.d1l.controller;

import com.opensymphony.xwork2.ActionSupport;
import com.d1l.service.DocumentGenerator;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DocumentController extends ActionSupport implements ServletResponseAware {

    private HttpServletResponse response;
    private int id;

    private void makeResponse(ByteArrayOutputStream stream, String contentType, String fileName) throws IOException {
        try {
        response.setContentType(contentType);
        response.setHeader("Content-Disposition",
                "inline; filename=" + fileName);
        response.setContentLength(stream.size());

        OutputStream os = response.getOutputStream();
        os.write(stream.toByteArray());
        os.flush();
        os.close();
        stream.reset();} catch (Exception exp){}
    }
    @Override
    public String execute() throws Exception {

        return super.execute();
    }

    public String getOrderPDF() throws IOException {
        try {
        makeResponse(DocumentGenerator.generateOrderInPDFById(getId()), "application/pdf", "order.pdf");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public String getOrdersXLS() throws IOException {
        try {
        makeResponse(DocumentGenerator.generateOrdersInXLS(), "application/vnd.ms-excel", "orders.xls");
        } catch (Exception ex) {

        }
        return NONE;
    }
    public String getOrdersCSV() throws IOException {
        try {
        makeResponse(DocumentGenerator.generateOrdersInCSV(), "text/csv", "orders.csv");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public String getMarketPDF() throws IOException {
        try {
        makeResponse(DocumentGenerator.generateMarketsInPDFById(getId()), "application/pdf", "market.pdf");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public String getMarketsXLS() throws IOException {
        try {
        makeResponse(DocumentGenerator.generateMarketsInXLS(), "application/vnd.ms-excel", "markets.xls");
        } catch (Exception ex) {

        }
        return NONE;
    }
    public String getMarketsCSV() throws IOException {
        try {
        makeResponse(DocumentGenerator.generateMarketsInCSV(), "text/csv", "markets.csv");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public String getCategoryPDF() throws IOException {
        try {
        makeResponse(DocumentGenerator.generateCategoriesInPDFById(getId()), "application/pdf", "category.pdf");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public String getCategoriesXLS() throws IOException {
        try {
        makeResponse(DocumentGenerator.generateCategoriesInXLS(), "application/vnd.ms-excel", "categories.xls");
        } catch (Exception ex) {

        }
        return NONE;
    }
    public String getCategoriesCSV() throws IOException {
        try {
        makeResponse(DocumentGenerator.generateCategoriesInCSV(), "text/csv", "categories.csv");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public String getUserPDF() throws IOException {
        try {
            makeResponse(DocumentGenerator.generateUsersInPDFById(getId()), "application/pdf", "user.pdf");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public String getUsersXLS() throws IOException {
        try {
            makeResponse(DocumentGenerator.generateUsersInXLS(), "application/vnd.ms-excel", "users.xls");
        } catch (Exception ex) {

        }
        return NONE;
    }
    public String getUsersCSV() throws IOException {
        try {
            makeResponse(DocumentGenerator.generateUsersInCSV(), "text/csv", "users.csv");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public String getItemPDF() throws IOException {
        try {
            makeResponse(DocumentGenerator.generateItemsInPDFById(getId()), "application/pdf", "item.pdf");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public String getItemsXLS() throws IOException {
        try {
            makeResponse(DocumentGenerator.generateItemsInXLS(), "application/vnd.ms-excel", "items.xls");
        } catch (Exception ex) {

        }
        return NONE;
    }
    public String getItemsCSV() throws IOException {
        try {
            makeResponse(DocumentGenerator.generateItemsInCSV(), "text/csv", "items.csv");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
