package com.ecommerce.Service;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.Entity.Address;
import com.ecommerce.Entity.hsndata;
import com.ecommerce.Entity.Order;
import com.ecommerce.Entity.Order_items;
import com.ecommerce.Repository.HsnRepository;
import com.ecommerce.Repository.OrderItemRepository;
import com.ecommerce.Repository.OrderRepository;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;

@Service
public class InvoiceService {

	private final OrderRepository orderRepository;
	private final OrderItemRepository orderItemRepository;

	public InvoiceService(OrderRepository orderRepository,
	                      OrderItemRepository orderItemRepository) {

	    this.orderRepository = orderRepository;
	    this.orderItemRepository = orderItemRepository;
	}

    public byte[] generateInvoice(int orderId) {

        try {

            Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new RuntimeException("Order not found"));

            List<Order_items> items = orderItemRepository.findByOrderId(orderId);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // ======================
            // HEADER
            // ======================
            document.add(new Paragraph("E-COMMERCE TAX INVOICE")
                    .setBold()
                    .setFontSize(18)
                    .setTextAlignment(TextAlignment.CENTER));

            document.add(new Paragraph("\n"));

            // ======================
            // ORDER DETAILS
            // ======================
            document.add(new Paragraph("Order ID: " + order.getId()));
            document.add(new Paragraph("Order Date: " + order.getCreated_at()));
            document.add(new Paragraph("Payment Status: " + order.getPayment_status()));

            document.add(new Paragraph("\n"));

            // ======================
            // CUSTOMER DETAILS
            // ======================
            document.add(new Paragraph("Customer: " + order.getUser().getName()));
            document.add(new Paragraph("Email: " + order.getUser().getEmail()));

            Address addr = order.getAddress();
            document.add(new Paragraph("Address: "
                    + addr.getStreet() + ", "
                    + addr.getCity() + ", "
                    + addr.getState() + " - "
                    + addr.getPincode()));

            document.add(new Paragraph("\n"));

            // ======================
            // CALCULATE SUBTOTAL FIRST
            // ======================
            BigDecimal subtotal = BigDecimal.ZERO;

            for (Order_items item : items) {
                subtotal = subtotal.add(
                        item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))
                );
            }

            // ======================
            // ORDER LEVEL DISCOUNT
            // ======================
            BigDecimal orderDiscount = order.getDiscount_amt() != null
                    ? order.getDiscount_amt()
                    : BigDecimal.ZERO;

            // ======================
            // PRODUCT TABLE
            // ======================
            float columnWidth[] = {130f, 70f, 60f, 40f, 50f, 70f, 70f, 70f};
            Table table = new Table(columnWidth);

            table.addHeaderCell("Product");
            table.addHeaderCell("HSN");
            table.addHeaderCell("Price");
            table.addHeaderCell("Qty");
            table.addHeaderCell("GST%");
            table.addHeaderCell("GST Amt");
            table.addHeaderCell("Discount");
            table.addHeaderCell("Total");

            BigDecimal totalGST = BigDecimal.ZERO;
            BigDecimal totalDiscount = BigDecimal.ZERO;

            for (Order_items item : items) {

                String productName = item.getProduct().getName();

                hsndata hsn =
                        item.getProduct()
                            .getCategory()
                            .getHsn();

                String hsnCode = hsn.getHsn_code();

                BigDecimal gstRate =
                        BigDecimal.valueOf(hsn.getGst_rate());

                BigDecimal price = item.getPrice();
                int qty = item.getQuantity();

                BigDecimal baseTotal = price.multiply(BigDecimal.valueOf(qty));

                // ITEM DISCOUNT
                BigDecimal itemDiscount = item.getDiscount_amt() != null
                        ? item.getDiscount_amt()
                        : BigDecimal.ZERO;

                // ✅ DISTRIBUTE ORDER DISCOUNT
                BigDecimal proportion = baseTotal.divide(subtotal, 6, RoundingMode.HALF_UP);
                BigDecimal orderShare = orderDiscount.multiply(proportion);

                // TOTAL DISCOUNT FOR ITEM
                BigDecimal totalItemDiscount = itemDiscount.add(orderShare);

                // TAXABLE VALUE
                BigDecimal taxable = baseTotal.subtract(totalItemDiscount);

                if (taxable.compareTo(BigDecimal.ZERO) < 0) {
                    taxable = BigDecimal.ZERO;
                }

                // GST
                BigDecimal gstAmount =
                        taxable.multiply(gstRate)
                               .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

                BigDecimal rowTotal = taxable.add(gstAmount);

                // ADD TO TABLE
                table.addCell(productName);
                table.addCell(hsnCode);
                table.addCell("₹" + price.setScale(2, RoundingMode.HALF_UP));
                table.addCell(String.valueOf(qty));
                table.addCell(gstRate + "%");
                table.addCell("₹" + gstAmount.setScale(2, RoundingMode.HALF_UP));
                table.addCell("₹" + totalItemDiscount.setScale(2, RoundingMode.HALF_UP));
                table.addCell("₹" + rowTotal.setScale(2, RoundingMode.HALF_UP));

                totalGST = totalGST.add(gstAmount);
                totalDiscount = totalDiscount.add(totalItemDiscount);
            }

            document.add(table);
            document.add(new Paragraph("\n"));

            // ======================
            // FINAL SUMMARY
            // ======================
            BigDecimal taxableValue = subtotal.subtract(totalDiscount);

            if (taxableValue.compareTo(BigDecimal.ZERO) < 0) {
                taxableValue = BigDecimal.ZERO;
            }

            BigDecimal cgst = totalGST.divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_UP);
            BigDecimal sgst = totalGST.divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_UP);

            BigDecimal finalAmount = taxableValue.add(totalGST);

            document.add(new Paragraph("Subtotal         : ₹ " + subtotal.setScale(2, RoundingMode.HALF_UP)));
            document.add(new Paragraph("Total Discount   : ₹ " + totalDiscount.setScale(2, RoundingMode.HALF_UP)));
            document.add(new Paragraph("Taxable Value    : ₹ " + taxableValue.setScale(2, RoundingMode.HALF_UP)));
            document.add(new Paragraph("CGST             : ₹ " + cgst.setScale(2, RoundingMode.HALF_UP)));
            document.add(new Paragraph("SGST             : ₹ " + sgst.setScale(2, RoundingMode.HALF_UP)));
            document.add(new Paragraph("Total GST        : ₹ " + totalGST.setScale(2, RoundingMode.HALF_UP)));
            document.add(new Paragraph("Final Amount     : ₹ " + finalAmount.setScale(2, RoundingMode.HALF_UP)).setBold());

            document.add(new Paragraph("\n"));
            document.add(new Paragraph("Thank you for shopping with us!")
                    .setTextAlignment(TextAlignment.CENTER));

            document.close();

            return out.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Invoice generation failed");
        }
    }
}