package service;

import com.d1l.controller.DocumentController;
import com.d1l.dao.ItemDao;
import com.d1l.dao.OrderDao;
import com.d1l.dao.UserDao;
import com.d1l.model.*;
import com.d1l.service.DocumentGenerator;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;
import com.opensymphony.xwork2.Action;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DocumentServiceTest {

    DocumentGenerator documentGenerator;
    DocumentController documentController;

    @Before
    public void setUp() throws Exception {

        documentGenerator = new DocumentGenerator();
        documentController = new DocumentController();
        documentController.setId(1);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addWaterMark() throws Exception {
        Document document = new Document(PageSize.A6, 30, 20, 20, 30);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, stream);
        pdfWriter.setEncryption(null, null, PdfWriter.ALLOW_PRINTING, PdfWriter.STANDARD_ENCRYPTION_128);
        document.open();
        documentGenerator.addWaterMark(pdfWriter);
    }

    @Test
    public void setCategoriesRow() throws Exception {
        documentGenerator.setCategoriesRow(new Category());
    }

    @Test
    public void setMarketsRow() throws Exception {
        documentGenerator.setMarketsRow(new Market());
    }

    @Test
    public void setOrdersRow() throws Exception {
        documentGenerator.setOrdersRow(OrderDao.getOrderById(1));
    }

    @Test
    public void setItemsRow() throws Exception {
        documentGenerator.setItemsRow(ItemDao.getItemById(1));
    }

    @Test
    public void setUsersRow() throws Exception {
        documentGenerator.setUsersRow(UserDao.getUserById(1));
    }

    @Test
    public void getUsersInPdf() throws Exception {
        documentGenerator.generateUsersInPDFById(1);
    }

    @Test
    public void getUsersInXls() throws Exception {
        documentGenerator.generateUsersInXLS();
    }

    @Test
    public void getUsersInCsv() throws Exception {
        documentGenerator.generateUsersInCSV();
    }

    @Test
    public void getCategoriesInXls() throws Exception {
        documentGenerator.generateCategoriesInXLS();
    }

    @Test
    public void getCategoriesInCsv() throws Exception {
        documentGenerator.generateCategoriesInCSV();
    }

    @Test
    public void getCategoriesInPdf() throws Exception {
        documentGenerator.generateCategoriesInPDFById(1);
    }

    @Test
    public void getMarketsInXls() throws Exception {
        documentGenerator.generateMarketsInXLS();
    }

    @Test
    public void getMarketsInCsv() throws Exception {
        documentGenerator.generateMarketsInCSV();
    }

    @Test
    public void getMarketsInPdf() throws Exception {
        documentGenerator.generateMarketsInPDFById(1);
    }

    @Test
    public void getOrdersInXls() throws Exception {
        documentGenerator.generateOrdersInXLS();
    }

    @Test
    public void getOrdersInCsv() throws Exception {
        documentGenerator.generateOrdersInCSV();
    }

    @Test
    public void getOrdersInPdf() throws Exception {
        documentGenerator.generateOrderInPDFById(1);
    }

    @Test
    public void getItemsInXls() throws Exception {
        documentGenerator.generateItemsInXLS();
    }

    @Test
    public void getItemsInCsv() throws Exception {
        documentGenerator.generateItemsInCSV();
    }

    @Test
    public void getItemsInPdf() throws Exception {
        documentGenerator.generateItemsInPDFById(1);
    }

    @Test
    public void getOrdersInCsvController() throws Exception {
        documentController.getOrdersCSV();
    }

    @Test
    public void getOrdersInPdfController() throws Exception {
        documentController.getOrderPDF();
    }

    @Test
    public void getOrdersInXlsController() throws Exception {
        documentController.getOrdersXLS();
    }

    @Test
    public void getItemsInCsvController() throws Exception {
        documentController.getItemsCSV();
    }

    @Test
    public void getItemsInPdfController() throws Exception {
        documentController.getItemPDF();
    }

    @Test
    public void getItemsInXlsController() throws Exception {
        documentController.getItemsXLS();
    }

    @Test
    public void getUsersInPdfController() throws Exception {
        documentController.getUserPDF();
    }

    @Test
    public void getUsersInXlsController() throws Exception {
        documentController.getUsersXLS();
    }

    @Test
    public void getUsersInCsvController() throws Exception {
        documentController.getUsersCSV();
    }

    @Test
    public void getCategoriesInPdfController() throws Exception {
        documentController.getCategoryPDF();
    }

    @Test
    public void getCategoriesInCsvController() throws Exception {
        documentController.getCategoriesCSV();
    }

    @Test
    public void getCategoriesInXlsController() throws Exception {
        documentController.getCategoriesXLS();
    }

    @Test
    public void getMarketsInXlsController() throws Exception {
        documentController.getMarketsXLS();
    }

    @Test
    public void getMarketsInCsvController() throws Exception {
        documentController.getMarketsCSV();
    }

    @Test
    public void getMarketsInPdfController() throws Exception {
        documentController.getMarketPDF();
    }

    @Test
    public void getMarketsPdfAnswer() throws Exception {
        assertEquals(Action.NONE, documentController.getMarketsXLS());
    }

    @Test
    public void getMarketsCsvAnswer() throws Exception {
        assertEquals(Action.NONE, documentController.getMarketsCSV());
    }

    @Test
    public void getMarketsXlsAnswer() throws Exception {
        assertEquals(Action.NONE, documentController.getMarketPDF());
    }

    @Test
    public void makeResponse() throws Exception {
        documentController.execute();
    }

}
