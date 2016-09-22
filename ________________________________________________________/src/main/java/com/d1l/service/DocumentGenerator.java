package com.d1l.service;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import com.opencsv.CSVWriter;
import com.d1l.dao.*;
import com.d1l.model.*;
import org.apache.poi.hssf.usermodel.*;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DocumentGenerator {

    private static final Font FONT_FOR_OBJECT_NAME = FontFactory.getFont(FontFactory.HELVETICA, 20,
            Font.BOLD);
    private static final Font COMMON_FONT = FontFactory.getFont(FontFactory.HELVETICA, 20);
    private static final String[] VISIT_HEADER = {"Visit Number", "Complaints", "Diagnosys"};
    private static final String[] ORDER_HEADER = {"Doctor", "Begin Time"};
    private static final String[] TREATMENTS_HEADER = {"Treatment Number", "Prescription", "Cure", "Cure Count",
                                                        "Using Method"};
    private static final String[] ANALYSE_HEADER = {"Analyse Number", "Name", "Result"};
    private static final String[] EMPTY_ARRAY = {""};

    private static void addWaterMark(PdfWriter writer) {
        Phrase watermark = new Phrase("Autoparts", FontFactory.getFont(FontFactory.HELVETICA, 40,
                Font.BOLD, Color.LIGHT_GRAY));
        Rectangle pageSize = writer.getPageSize();
        float x = (pageSize.getLeft() + pageSize.getRight()) / 2;
        float y = (pageSize.getTop() + pageSize.getBottom()) / 2;
        PdfContentByte canvas = writer.getDirectContentUnder();
        ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, watermark, x, y, 45);
    }

    private static List<String> setOrdersRow(Order order ){
        List<String> ordersRow = new LinkedList<String>();
        List<OrderItem> orderItems = OrderItemDao.getOrderItemsByOrderId(order.getId());
        Customer customer = order.getCustomer();
        ordersRow.add(String.format("%d", order.getId()));

        ordersRow.add(String.format("%s ", customer.getFullname()));

        ordersRow.add(String.format("%s", order.getDate()));

        List<ItemReport> details = new ArrayList<ItemReport>();
        for (OrderItem od : orderItems) {
            Item i = ItemDao.getItemById(od.getItemId());
            ItemReport report = new ItemReport();
            report.setCount(od.getCount());
            report.setItem(i);
            details.add(report);
        }

        for (ItemReport d : details) {
            ordersRow.add(String.format("count: %s; name: %s, %s$ by %s; category: %s ; market: %s, %s;", d.getCount(),
                    d.getItem().getName(), d.getItem().getPrice(),
                    d.getItem().getSupplier().getCompanyName(), d.getItem().getCategory().getName(),
                    d.getItem().getMarket().getName(),
                    d.getItem().getMarket().getAddress()));

        }

        return ordersRow;
    }

    public static ByteArrayOutputStream generateOrderInPDFById(int id) {
        Document document = new Document(PageSize.A6, 20, 20, 20, 20);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = null;
        try {
            pdfWriter = PdfWriter.getInstance(document, stream);
            document.open();
            addWaterMark(pdfWriter);
            Order order = OrderDao.getOrderById(id);
            List<String> ordersRow = setOrdersRow(order);
            Paragraph orderNumber = new Paragraph();
            orderNumber.add(new Chunk("Order #", FONT_FOR_OBJECT_NAME));
            orderNumber.add(new Chunk(ordersRow.get(0), COMMON_FONT));
            orderNumber.setAlignment(Element.ALIGN_CENTER);
            document.add(orderNumber);
            document.add(Chunk.NEWLINE);
            Paragraph clientName = new Paragraph();
            clientName.add(new Chunk("Customer: ", FONT_FOR_OBJECT_NAME));
            clientName.add(new Chunk(ordersRow.get(1), COMMON_FONT));
            document.add(clientName);
            document.add(Chunk.NEWLINE);
            Paragraph time = new Paragraph();
            time.add(new Chunk("Begin time: ", FONT_FOR_OBJECT_NAME));
            time.add(new Chunk(ordersRow.get(2), COMMON_FONT));
            document.add(time);
            document.add(Chunk.NEWLINE);
            Paragraph details = new Paragraph();
            details.add(new Chunk("Items: ", FONT_FOR_OBJECT_NAME));
            document.add(details);
            document.add(Chunk.NEWLINE);
            for (int i = 3; i < ordersRow.size(); i++) {
                Paragraph detail = new Paragraph();
                detail.add(new Chunk("Item #" + (i - 2) + ": ", FONT_FOR_OBJECT_NAME));
                detail.add(new Chunk(ordersRow.get(i), COMMON_FONT));
                document.add(detail);
                document.add(Chunk.NEWLINE);
            }

            document.addAuthor("Autoparts Systems");
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
            if (pdfWriter != null) {
                document.close();
            }
        }
        return stream;
    }

    public static ByteArrayOutputStream generateOrdersInXLS() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("order");
        HSSFCellStyle headerCellStyle = workbook.createCellStyle();
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont boldFont = workbook.createFont();
        boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerCellStyle.setFont(boldFont);

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Order Number"));
        cell = row.createCell(1);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Customer"));
        cell = row.createCell(2);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Date"));
        cell = row.createCell(3);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Items"));

        sheet.autoSizeColumn(0);
        headerCellStyle.setWrapText(true);
        style.setWrapText(true);
        int[] columnWidths = {20, 20, 20, 20};
        for (int i = 0; i < columnWidths.length; i++) {
            columnWidths[i] = columnWidths[i] * 256;
        }

        List<Order> orderList = OrderDao.getOrdersList();
        for (int i = 0; i < orderList.size(); i++ ) {
            row = sheet.createRow(i+1);
            row.setRowStyle(style);
            List<String> ordersRow = setOrdersRow(orderList.get(i));
            for (int j = 0; j < 4; j ++) {
                cell = row.createCell(j);
                cell.setCellStyle(style);
                HSSFRichTextString orderNumberCellValue;
                if (j != 3)
                    orderNumberCellValue = new HSSFRichTextString(ordersRow.get(j));
                else {
                    String result = "";
                    for (int k = 3; k < ordersRow.size(); k++) {
                        result += ordersRow.get(k) + "\n";
                    }
                    orderNumberCellValue = new HSSFRichTextString(result);
                }
                sheet.autoSizeColumn(j);
                cell.setCellValue(orderNumberCellValue);
                sheet.autoSizeColumn(j);

                sheet.setColumnWidth(j, columnWidths[j]);
            }

        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        workbook.write(stream);
        return  stream;
    }

    public static ByteArrayOutputStream generateOrdersInCSV() throws IOException {
        String[] fileHeader = {"Order Number", "Customer", "Date", "Items"};
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        CSVWriter writer = new CSVWriter(new OutputStreamWriter(stream, Charset.forName("UTF-8")), ',');
        writer.writeNext(fileHeader);
        List<String[]> ordersInString = new LinkedList<String[]>();
        List<Order> orderList = OrderDao.getOrdersList();
        for (int i = 0; i < orderList.size(); i++) {
            List<String> ordersRow = setOrdersRow(orderList.get(i));

            String result = "";
            for (int k = 3; k < ordersRow.size(); k++) {
                result += ordersRow.get(k) + "\n";
            }

            String[] tempArray = {ordersRow.get(0), ordersRow.get(1), ordersRow.get(2), result};
            ordersInString.add(tempArray);
        }

        writer.writeAll(ordersInString);
        writer.close();
        return stream;
    }

    private static List<String> setMarketsRow(Market market) {
        List<String> marketsRow = new LinkedList<String>();
        marketsRow.add(String.format("%d", market.getId()));

        marketsRow.add(String.format("%s ", market.getName()));

        marketsRow.add(String.format("%s ", market.getAddress()));

        return marketsRow;
    }


    public static ByteArrayOutputStream generateMarketsInPDFById(int id) {
        Document document = new Document(PageSize.A6, 30, 20, 20, 30);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = null;
        try {
            pdfWriter = PdfWriter.getInstance(document, stream);
            document.open();
            addWaterMark(pdfWriter);
            Market market = MarketDao.getMarketById(id);
            List<String> marketsRow = setMarketsRow(market);

            Paragraph marketNumber = new Paragraph();
            marketNumber.add(new Chunk("Market #", FONT_FOR_OBJECT_NAME));
            marketNumber.add(new Chunk(marketsRow.get(0), COMMON_FONT));
            marketNumber.setAlignment(Element.ALIGN_CENTER);
            document.add(marketNumber);
            document.add(Chunk.NEWLINE);
            Paragraph name = new Paragraph();
            name.add(new Chunk("Name: ", FONT_FOR_OBJECT_NAME));
            name.add(new Chunk(marketsRow.get(1), COMMON_FONT));
            document.add(name);
            document.add(Chunk.NEWLINE);
            Paragraph address = new Paragraph();
            address.add(new Chunk("Address: ", FONT_FOR_OBJECT_NAME));
            address.add(new Chunk(marketsRow.get(2), COMMON_FONT));
            document.add(address);
            document.add(Chunk.NEWLINE);
            document.addAuthor("Market Service Systems");
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
            if (pdfWriter != null) {
                document.close();
            }
        }
        return stream;
    }

    public static ByteArrayOutputStream generateMarketsInXLS() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("market");

        HSSFCellStyle style = workbook.createCellStyle();
        HSSFCellStyle headerCellStyle = workbook.createCellStyle();

        HSSFFont boldFont = workbook.createFont();

        boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerCellStyle.setFont(boldFont);

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Market Number"));
        cell = row.createCell(1);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Name"));
        cell = row.createCell(2);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Address"));
        sheet.autoSizeColumn(0);
        style.setWrapText(true);
        headerCellStyle.setWrapText(true);
        int[] columnWidths = {17, 10, 10, 20, 20};
        for (int i = 0; i < columnWidths.length; i++) {
            columnWidths[i] = columnWidths[i] * 256;
        }
        List<Market> marketsList = MarketDao.getMarketsList();
        for (int i = 0; i < marketsList.size(); i++ ) {
            row = sheet.createRow(i+1);
            row.setRowStyle(style);
            List<String> ordersRow = setMarketsRow(marketsList.get(i));
            for (int j = 0; j < ordersRow.size(); j ++) {
                cell = row.createCell(j);
                cell.setCellStyle(style);
                HSSFRichTextString orderNumberCellValue = new HSSFRichTextString(ordersRow.get(j));

                cell.setCellValue(orderNumberCellValue);
                sheet.autoSizeColumn(j);

                sheet.setColumnWidth(j, columnWidths[j]);

            }

        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        workbook.write(stream);
        return  stream;
    }

    public static ByteArrayOutputStream generateMarketsInCSV() throws IOException {
        String[] fileHeader = {"Market Number", "Name", "Address"};
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        CSVWriter writer = new CSVWriter(new OutputStreamWriter(stream, Charset.forName("UTF-8")), ',');
        writer.writeNext(fileHeader);
        List<String[]> marketsInString = new LinkedList<String[]>();
        List<Market> marketsList = MarketDao.getMarketsList();
        for (int i = 0; i < marketsList.size(); i++) {
            List<String> analysesRow = setMarketsRow(marketsList.get(i));
            String[] tempArray = {analysesRow.get(0), analysesRow.get(1), analysesRow.get(2)};
            marketsInString.add(tempArray);
        }

        writer.writeAll(marketsInString);
        writer.close();
        return stream;
    }

    private static List<String> setCategoriesRow(Category category) {
        List<String> categoriesRow = new LinkedList<String>();
        categoriesRow.add(String.format("%d", category.getId()));

        categoriesRow.add(String.format("%s ", category.getName()));

        return categoriesRow;
    }


    public static ByteArrayOutputStream generateCategoriesInPDFById(int id) {
        Document document = new Document(PageSize.A6, 30, 20, 20, 30);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = null;
        try {
            pdfWriter = PdfWriter.getInstance(document, stream);
            document.open();
            addWaterMark(pdfWriter);
            Category category = CategoryDao.getCategoryById(id);
            List<String> categoriesRow = setCategoriesRow(category);

            Paragraph categoryNumber = new Paragraph();
            categoryNumber.add(new Chunk("Category #", FONT_FOR_OBJECT_NAME));
            categoryNumber.add(new Chunk(categoriesRow.get(0), COMMON_FONT));
            categoryNumber.setAlignment(Element.ALIGN_CENTER);
            document.add(categoryNumber);
            document.add(Chunk.NEWLINE);
            Paragraph name = new Paragraph();
            name.add(new Chunk("Name: ", FONT_FOR_OBJECT_NAME));
            name.add(new Chunk(categoriesRow.get(1), COMMON_FONT));
            document.add(name);
            document.add(Chunk.NEWLINE);
            document.addAuthor("Market Service Systems");
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
            if (pdfWriter != null) {
                document.close();
            }
        }
        return stream;
    }

    public static ByteArrayOutputStream generateCategoriesInXLS() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("category");

        HSSFCellStyle style = workbook.createCellStyle();
        HSSFCellStyle headerCellStyle = workbook.createCellStyle();

        HSSFFont boldFont = workbook.createFont();

        boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerCellStyle.setFont(boldFont);

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Category Number"));
        cell = row.createCell(1);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Name"));
        sheet.autoSizeColumn(0);
        style.setWrapText(true);
        headerCellStyle.setWrapText(true);
        int[] columnWidths = {17, 10, 10, 20, 20};
        for (int i = 0; i < columnWidths.length; i++) {
            columnWidths[i] = columnWidths[i] * 256;
        }
        List<Category> categoriesList = CategoryDao.getCategoriesList();
        for (int i = 0; i < categoriesList.size(); i++ ) {
            row = sheet.createRow(i+1);
            row.setRowStyle(style);
            List<String> carsRow = setCategoriesRow(categoriesList.get(i));
            for (int j = 0; j < carsRow.size(); j ++) {
                cell = row.createCell(j);
                cell.setCellStyle(style);
                HSSFRichTextString orderNumberCellValue = new HSSFRichTextString(carsRow.get(j));

                cell.setCellValue(orderNumberCellValue);
                sheet.autoSizeColumn(j);

                sheet.setColumnWidth(j, columnWidths[j]);

            }

        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        workbook.write(stream);
        return  stream;
    }

    public static ByteArrayOutputStream generateCategoriesInCSV() throws IOException {
        String[] fileHeader = {"Category Number", "Name"};
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        CSVWriter writer = new CSVWriter(new OutputStreamWriter(stream, Charset.forName("UTF-8")), ',');
        writer.writeNext(fileHeader);
        List<String[]> categoriesInString = new LinkedList<String[]>();
        List<Category> categoriesList = CategoryDao.getCategoriesList();
        for (int i = 0; i < categoriesList.size(); i++) {
            List<String> categoriesRow = setCategoriesRow(categoriesList.get(i));
            String[] tempArray = {categoriesRow.get(0), categoriesRow.get(1)};
            categoriesInString.add(tempArray);
        }

        writer.writeAll(categoriesInString);
        writer.close();
        return stream;
    }


}
