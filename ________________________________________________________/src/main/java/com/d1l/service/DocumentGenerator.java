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

    public static void addWaterMark(PdfWriter writer) {
        Phrase watermark = new Phrase("Autoparts", FontFactory.getFont(FontFactory.HELVETICA, 40,
                Font.BOLD, Color.LIGHT_GRAY));
        Rectangle pageSize = writer.getPageSize();
        float x = (pageSize.getLeft() + pageSize.getRight()) / 2;
        float y = (pageSize.getTop() + pageSize.getBottom()) / 2;
        PdfContentByte canvas = writer.getDirectContentUnder();
        ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, watermark, x, y, 45);
    }

    public static List<String> setOrdersRow(Order order ){
        List<String> ordersRow = new LinkedList<String>();
        List<OrderItem> orderItems = OrderItemDao.getOrderItemsByOrderId(order.getId());
        Customer customer = order.getCustomer();
        ordersRow.add(String.format("%d", order.getId()));

        ordersRow.add(String.format("%s ", customer.getFullname()));

        ordersRow.add(String.format("%s", order.getDate()));

        List<ItemReport> Items = new ArrayList<ItemReport>();
        for (OrderItem od : orderItems) {
            Item i = ItemDao.getItemById(od.getItemId());
            ItemReport report = new ItemReport();
            report.setCount(od.getCount());
            report.setItem(i);
            Items.add(report);
        }

        for (ItemReport d : Items) {
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
            pdfWriter.setEncryption(null, null, PdfWriter.ALLOW_PRINTING, PdfWriter.STANDARD_ENCRYPTION_128);
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
            Paragraph Items = new Paragraph();
            Items.add(new Chunk("Items: ", FONT_FOR_OBJECT_NAME));
            document.add(Items);
            document.add(Chunk.NEWLINE);
            for (int i = 3; i < ordersRow.size(); i++) {
                Paragraph Item = new Paragraph();
                Item.add(new Chunk("Item #" + (i - 2) + ": ", FONT_FOR_OBJECT_NAME));
                Item.add(new Chunk(ordersRow.get(i), COMMON_FONT));
                document.add(Item);
                document.add(Chunk.NEWLINE);
            }

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

    public static List<String> setMarketsRow(Market market) {
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
            pdfWriter.setEncryption(null, null, PdfWriter.ALLOW_PRINTING, PdfWriter.STANDARD_ENCRYPTION_128);
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

    public static List<String> setCategoriesRow(Category category) {
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
            pdfWriter.setEncryption(null, null, PdfWriter.ALLOW_PRINTING, PdfWriter.STANDARD_ENCRYPTION_128);
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

    public static List<String> setItemsRow(Item item) {
        List<String> ItemsRow = new LinkedList<String>();
        ItemsRow.add(String.format("%d", item.getId()));

        ItemsRow.add(String.format("%s ", item.getName()));

        ItemsRow.add(String.format("%s ", item.getSupplier().getCompanyName()));

        ItemsRow.add(String.format("%s ", item.getCategory().getName()));

        ItemsRow.add(String.format("%s ", item.getMarket().getName()));

        ItemsRow.add(String.format("%s ", item.getMarket().getAddress()));

        ItemsRow.add(String.format("%s ", item.getPrice()));

        return ItemsRow;
    }


    public static ByteArrayOutputStream generateItemsInPDFById(int id) {
        Document document = new Document(PageSize.A6, 30, 20, 20, 30);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = null;
        try {
            pdfWriter = PdfWriter.getInstance(document, stream);
            pdfWriter.setEncryption(null, null, PdfWriter.ALLOW_PRINTING, PdfWriter.STANDARD_ENCRYPTION_128);
            document.open();
            addWaterMark(pdfWriter);
            Item Item = ItemDao.getItemById(id);
            List<String> ItemsRow = setItemsRow(Item);

            Paragraph ItemNumber = new Paragraph();
            ItemNumber.add(new Chunk("Item #", FONT_FOR_OBJECT_NAME));
            ItemNumber.add(new Chunk(ItemsRow.get(0), COMMON_FONT));
            ItemNumber.setAlignment(Element.ALIGN_CENTER);
            document.add(ItemNumber);
            document.add(Chunk.NEWLINE);

            Paragraph name = new Paragraph();
            name.add(new Chunk("Name: ", FONT_FOR_OBJECT_NAME));
            name.add(new Chunk(ItemsRow.get(1), COMMON_FONT));
            document.add(name);
            document.add(Chunk.NEWLINE);

            Paragraph supplierName = new Paragraph();
            supplierName.add(new Chunk("Supplier name: ", FONT_FOR_OBJECT_NAME));
            supplierName.add(new Chunk(ItemsRow.get(2), COMMON_FONT));
            document.add(supplierName);
            document.add(Chunk.NEWLINE);

            Paragraph categoryName = new Paragraph();
            categoryName.add(new Chunk("Category name: ", FONT_FOR_OBJECT_NAME));
            categoryName.add(new Chunk(ItemsRow.get(3), COMMON_FONT));
            document.add(categoryName);
            document.add(Chunk.NEWLINE);

            Paragraph marketName = new Paragraph();
            marketName.add(new Chunk("Market name: ", FONT_FOR_OBJECT_NAME));
            marketName.add(new Chunk(ItemsRow.get(4), COMMON_FONT));
            document.add(marketName);
            document.add(Chunk.NEWLINE);

            Paragraph marketAddress = new Paragraph();
            marketAddress.add(new Chunk("Market address: ", FONT_FOR_OBJECT_NAME));
            marketAddress.add(new Chunk(ItemsRow.get(5), COMMON_FONT));
            document.add(marketAddress);
            document.add(Chunk.NEWLINE);

            Paragraph price = new Paragraph();
            price.add(new Chunk("Price: ", FONT_FOR_OBJECT_NAME));
            price.add(new Chunk(ItemsRow.get(6), COMMON_FONT));
            document.add(price);
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

    public static ByteArrayOutputStream generateItemsInXLS() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Item");

        HSSFCellStyle style = workbook.createCellStyle();
        HSSFCellStyle headerCellStyle = workbook.createCellStyle();

        HSSFFont boldFont = workbook.createFont();

        boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerCellStyle.setFont(boldFont);

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Item Number"));
        cell = row.createCell(1);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Name"));
        cell = row.createCell(2);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Supplier name"));
        cell = row.createCell(3);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Category name"));
        cell = row.createCell(4);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Market name"));
        cell = row.createCell(5);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Market address"));
        cell = row.createCell(6);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Price"));
        sheet.autoSizeColumn(0);
        style.setWrapText(true);
        headerCellStyle.setWrapText(true);
        int[] columnWidths = {17, 10, 10, 20, 20};
        for (int i = 0; i < columnWidths.length; i++) {
            columnWidths[i] = columnWidths[i] * 256;
        }
        List<Item> ItemList = ItemDao.getItemsList();
        for (int i = 0; i < ItemList.size(); i++ ) {
            row = sheet.createRow(i+1);
            row.setRowStyle(style);
            List<String> ItemsRow = setItemsRow(ItemList.get(i));
            for (int j = 0; j < ItemsRow.size(); j++) {
                cell = row.createCell(j);
                cell.setCellStyle(style);
                HSSFRichTextString ItemNumberCellValue = new HSSFRichTextString(ItemsRow.get(j));

                cell.setCellValue(ItemNumberCellValue);
                sheet.autoSizeColumn(j);

                //sheet.setColumnWidth(j, columnWidths[j]);

            }

        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        workbook.write(stream);
        return  stream;
    }

    public static ByteArrayOutputStream generateItemsInCSV() throws IOException {
        String[] fileHeader = {"Item Number", "Name", "Supplier name", "Category name", "Market name", "Market year", "Price"};
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        CSVWriter writer = new CSVWriter(new OutputStreamWriter(stream, Charset.forName("UTF-8")), ',');
        writer.writeNext(fileHeader);
        List<String[]> ItemsInString = new LinkedList<String[]>();
        List<Item> ItemsList = ItemDao.getItemsList();
        for (int i = 0; i < ItemsList.size(); i++) {
            List<String> ItemsRow = setItemsRow(ItemsList.get(i));
            String[] tempArray = {ItemsRow.get(0), ItemsRow.get(1), ItemsRow.get(2), ItemsRow.get(3),
                    ItemsRow.get(4), ItemsRow.get(5), ItemsRow.get(6)};
            ItemsInString.add(tempArray);
        }

        writer.writeAll(ItemsInString);
        writer.close();
        return stream;
    }

    public static List<String> setUsersRow(User user) {
        List<String> usersRow = new LinkedList<String>();
        usersRow.add(String.format("%d", user.getId()));

        usersRow.add(String.format("%s ", user.getLogin()));

        usersRow.add(String.format("%s ", user.getRole().getName()));

        return usersRow;
    }

    public static ByteArrayOutputStream generateUsersInPDFById(int id) {
        Document document = new Document(PageSize.A6, 30, 20, 20, 30);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = null;
        try {
            pdfWriter = PdfWriter.getInstance(document, stream);
            pdfWriter.setEncryption(null, null, PdfWriter.ALLOW_PRINTING, PdfWriter.STANDARD_ENCRYPTION_128);
            document.open();
            addWaterMark(pdfWriter);
            User user = UserDao.getUserById(id);
            List<String> usersRow = setUsersRow(user);

            Paragraph userNumber = new Paragraph();
            userNumber.add(new Chunk("User #", FONT_FOR_OBJECT_NAME));
            userNumber.add(new Chunk(usersRow.get(0), COMMON_FONT));
            userNumber.setAlignment(Element.ALIGN_CENTER);
            document.add(userNumber);
            document.add(Chunk.NEWLINE);
            Paragraph name = new Paragraph();
            name.add(new Chunk("Login: ", FONT_FOR_OBJECT_NAME));
            name.add(new Chunk(usersRow.get(1), COMMON_FONT));
            document.add(name);
            document.add(Chunk.NEWLINE);
            Paragraph role = new Paragraph();
            role.add(new Chunk("Role: ", FONT_FOR_OBJECT_NAME));
            role.add(new Chunk(usersRow.get(2), COMMON_FONT));
            document.add(role);
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

    public static ByteArrayOutputStream generateUsersInXLS() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("user");

        HSSFCellStyle style = workbook.createCellStyle();
        HSSFCellStyle headerCellStyle = workbook.createCellStyle();

        HSSFFont boldFont = workbook.createFont();

        boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerCellStyle.setFont(boldFont);

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("User Number"));
        cell = row.createCell(1);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Login"));
        cell = row.createCell(2);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Role"));
        sheet.autoSizeColumn(0);
        style.setWrapText(true);
        headerCellStyle.setWrapText(true);
        int[] columnWidths = {17, 10, 10, 20, 20};
        for (int i = 0; i < columnWidths.length; i++) {
            columnWidths[i] = columnWidths[i] * 256;
        }
        List<User> userList = UserDao.getUsersList();
        for (int i = 0; i < userList.size(); i++ ) {
            row = sheet.createRow(i+1);
            row.setRowStyle(style);
            List<String> usersRow = setUsersRow(userList.get(i));
            for (int j = 0; j < usersRow.size(); j ++) {
                cell = row.createCell(j);
                cell.setCellStyle(style);
                HSSFRichTextString userNumberCellValue = new HSSFRichTextString(usersRow.get(j));

                cell.setCellValue(userNumberCellValue);
                sheet.autoSizeColumn(j);

                sheet.setColumnWidth(j, columnWidths[j]);

            }

        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        workbook.write(stream);
        return  stream;
    }

    public static ByteArrayOutputStream generateUsersInCSV() throws IOException {
        String[] fileHeader = {"User Number", "Login", "Role"};
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        CSVWriter writer = new CSVWriter(new OutputStreamWriter(stream, Charset.forName("UTF-8")), ',');
        writer.writeNext(fileHeader);
        List<String[]> usersInString = new LinkedList<String[]>();
        List<User> userList = UserDao.getUsersList();
        for (int i = 0; i < userList.size(); i++) {
            List<String> usersRow = setUsersRow(userList.get(i));
            String[] tempArray = {usersRow.get(0), usersRow.get(1), usersRow.get(2)};
            usersInString.add(tempArray);
        }

        writer.writeAll(usersInString);
        writer.close();
        return stream;
    }

}

