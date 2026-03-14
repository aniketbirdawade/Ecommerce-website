package com.ecommerce.Service;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.Entity.Address;
import com.ecommerce.Entity.Order;
import com.ecommerce.Entity.Order_items;
import com.ecommerce.Repository.OrderItemRepository;
import com.ecommerce.Repository.OrderRepository;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;

@Service
public class InvoiceService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public byte[] generateInvoice(int orderId) {

        try {

            Order order = orderRepository.findById(orderId).orElseThrow();

            List<Order_items> items = orderItemRepository.findByOrderId(orderId);

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // TITLE
            Paragraph title = new Paragraph("E-COMMERCE INVOICE")
                    .setBold()
                    .setFontSize(20)
                    .setTextAlignment(TextAlignment.CENTER);

            document.add(title);

            document.add(new Paragraph("\n"));

            // ORDER DETAILS
            document.add(new Paragraph("Order ID: " + order.getId()));
            document.add(new Paragraph("Order Date: " + order.getCreated_at()));
            document.add(new Paragraph("Payment Status: " + order.getPayment_status()));

            document.add(new Paragraph("\n"));

            // CUSTOMER DETAILS
            document.add(new Paragraph("Customer Name: " + order.getUser().getName()));
            document.add(new Paragraph("Email: " + order.getUser().getEmail()));
            document.add(new Paragraph("Mobile: " + order.getUser().getMobile()));

            document.add(new Paragraph("\n"));

            Address addr = order.getAddress();

            document.add(new Paragraph("Shipping Address:"));
            document.add(new Paragraph(
                    addr.getStreet() + ", " +
                    addr.getCity() + ", " +
                    addr.getState() + " - " +
                    addr.getPincode()
            ));

            document.add(new Paragraph("\n"));

            // PRODUCT TABLE
            float columnWidth[] = {200f, 80f, 80f, 80f, 80f};

            Table table = new Table(columnWidth);

            table.addHeaderCell("Product");
            table.addHeaderCell("Price");
            table.addHeaderCell("Qty");
            table.addHeaderCell("GST");
            table.addHeaderCell("Total");

            BigDecimal grandTotal = BigDecimal.ZERO;

            for (Order_items item : items) {

                String productName = item.getProduct().getName();

                BigDecimal price = item.getPrice();

                int qty = item.getQuantity();

                BigDecimal gst = item.getGst_amount();

                BigDecimal total = item.getTotal_price();

                table.addCell(productName);
                table.addCell(price.toString());
                table.addCell(String.valueOf(qty));
                table.addCell(gst.toString());
                table.addCell(total.toString());

                grandTotal = grandTotal.add(total);
            }

            document.add(table);

            document.add(new Paragraph("\n"));

            // PRICE SUMMARY
            BigDecimal discount = order.getDiscount_amt() != null ? order.getDiscount_amt() : BigDecimal.ZERO;

            BigDecimal finalAmount = grandTotal.subtract(discount);

            document.add(new Paragraph("Total Amount : ₹ " + grandTotal));
            document.add(new Paragraph("Discount : ₹ " + discount));
            document.add(new Paragraph("Final Amount : ₹ " + finalAmount).setBold());
            

            document.add(new Paragraph("\n"));
            document.add(new Paragraph("Thank you for shopping with us!")
                    .setTextAlignment(TextAlignment.CENTER));

            document.close();

            return out.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}