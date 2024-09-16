package com.example.iticketfinal.service;

import com.itextpdf.io.exceptions.IOException;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.itextpdf.layout.Document;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;

    public void postEmail(String adress, String subject, String body) {
        log.info("ACTION.postEmail.start adress : {} | subject : {}", adress, subject);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(System.getenv("mail_username"));
        message.setTo(adress);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    public void postEmailWithAttachment(String address, String subject, String body) throws MessagingException, IOException {
        log.info("ACTION.postEmailWithAttachment.start address : {} | subject : {}", address, subject);

        // Create the PDF
        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(pdfOutputStream);
        com.itextpdf.kernel.pdf.PdfDocument pdfDoc = new com.itextpdf.kernel.pdf.PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Ticket Details")); // Add ticket information here
        document.add(new Paragraph(body));
        document.close();

        // Send email with the PDF attachment
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

//        helper.setFrom(System.getenv("mail_username"));
        helper.setFrom("qasimovbaloglan13@gmail.com");
        helper.setTo(address);
        helper.setSubject(subject);
        helper.setText(body);

        // Attach the PDF as a byte array
        helper.addAttachment("ticket.pdf" + UUID.randomUUID(), new ByteArrayDataSource(pdfOutputStream.toByteArray(), "application/pdf"));

        mailSender.send(mimeMessage);

        log.info("Email sent to {} with subject {}", address, subject);
    }

    public void postEmailWithAttachmentv2(String address, String subject, String eventName, String category, Double price) throws MessagingException {
        log.info("ACTION.postEmailWithAttachment.start address : {} | subject : {}", address, subject);

        // Create the PDF
        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(pdfOutputStream);
        com.itextpdf.kernel.pdf.PdfDocument pdfDoc = new com.itextpdf.kernel.pdf.PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // Header - Event Name
        Paragraph header = new Paragraph("Event Ticket")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(18)
                .setBold()
                .setMarginBottom(20);

        // Event Info Table
        Table table = new Table(UnitValue.createPercentArray(new float[]{2, 5}));
        table.setWidth(UnitValue.createPercentValue(100));

        // Adding rows to the table
        table.addCell("Event:");
        table.addCell(eventName);

        table.addCell("Category:");
        table.addCell(category);

        table.addCell("Price:");
        table.addCell(String.valueOf(price));

        // Add details with some formatting
        Paragraph eventDetails = new Paragraph()
                .add(new Text("Event Name: ").setBold())
                .add(eventName + "\n")
                .add(new Text("Category: ").setBold())
                .add(category + "\n")
                .add(new Text("Price: ").setBold())
                .add(price + "\n");

        // Add a footer note
        Paragraph footer = new Paragraph("Thank you for purchasing your ticket!")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(12)
                .setFontColor(ColorConstants.GRAY)
                .setMarginTop(30);

        // Add elements to the document
        document.add(header);
        document.add(table);
        document.add(footer);
        document.close();

        // Send email with the PDF attachment
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom("your_email@example.com");
        helper.setTo(address);
        helper.setSubject(subject);
        helper.setText("Please find your ticket attached below.");

        // Attach the PDF
        helper.addAttachment("ticket.pdf", new ByteArrayDataSource(pdfOutputStream.toByteArray(), "application/pdf"));

        mailSender.send(mimeMessage);
        log.info("Email sent to {} with subject {}", address, subject);
    }

}

