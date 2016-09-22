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
        response.setContentType(contentType);
        response.setHeader("Content-Disposition",
                "inline; filename=" + fileName);
        response.setContentLength(stream.size());

        OutputStream os = response.getOutputStream();
        os.write(stream.toByteArray());
        os.flush();
        os.close();
        stream.reset();
    }
    @Override
    public String execute() throws Exception {

        return super.execute();
    }

    public String getOrderPDF() throws IOException {
        makeResponse(DocumentGenerator.generateOrderInPDFById(getId()), "application/pdf", "order.pdf");
        return NONE;
    }

    public String getOrdersXLS() throws IOException {
        makeResponse(DocumentGenerator.generateOrdersInXLS(), "application/vnd.ms-excel", "orders.xls");
        return NONE;
    }
    public String getOrdersCSV() throws IOException {
        makeResponse(DocumentGenerator.generateOrdersInCSV(), "text/csv", "orders.csv");
        return NONE;
    }

    public String getMarketPDF() throws IOException {
        makeResponse(DocumentGenerator.generateMarketsInPDFById(getId()), "application/pdf", "market.pdf");
        return NONE;
    }

    public String getMarketsXLS() throws IOException {
        makeResponse(DocumentGenerator.generateMarketsInXLS(), "application/vnd.ms-excel", "markets.xls");
        return NONE;
    }
    public String getMarketsCSV() throws IOException {
        makeResponse(DocumentGenerator.generateMarketsInCSV(), "text/csv", "markets.csv");
        return NONE;
    }

    public String getCategoryPDF() throws IOException {
        makeResponse(DocumentGenerator.generateCategoriesInPDFById(getId()), "application/pdf", "category.pdf");
        return NONE;
    }

    public String getCategoriesXLS() throws IOException {
        makeResponse(DocumentGenerator.generateCategoriesInXLS(), "application/vnd.ms-excel", "categories.xls");
        return NONE;
    }
    public String getCategoriesCSV() throws IOException {
        makeResponse(DocumentGenerator.generateCategoriesInCSV(), "text/csv", "categories.csv");
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
