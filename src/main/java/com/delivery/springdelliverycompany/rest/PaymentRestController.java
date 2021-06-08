package com.delivery.springdelliverycompany.rest;

import com.delivery.springdelliverycompany.controller.AuthController;
import com.delivery.springdelliverycompany.model.Address;
import com.delivery.springdelliverycompany.model.Card;
import com.delivery.springdelliverycompany.model.Cart;
import com.delivery.springdelliverycompany.repository.AddressRepository;
import com.delivery.springdelliverycompany.repository.CardRepository;
import com.delivery.springdelliverycompany.repository.CartRepository;
import com.delivery.springdelliverycompany.repository.UserRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/cart")
public class PaymentRestController {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LogManager.getLogger(PaymentRestController.class);

    @Autowired
    private CardRepository cardRepo;

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private AddressRepository addressRepo;

    @GetMapping("/addr")
    public List<Address> getAddresses()
    {
        return addressRepo.findAll();
    }

    @PostMapping(path="/addressdata", consumes= {"application/json"})
    public String postAddrress(@RequestBody Address address) {

        addressRepo.save(address);

        logger.info(address);
        return "true";
    }

    @GetMapping("/card")
    public List<Card> getCards()
    {
        return cardRepo.findAll();
    }

    @PostMapping(path="/paymentdata", consumes= {"application/json"})
    public String postCard(@RequestBody Card card) {
        cardRepo.save(card);
        logger.info(card);

        return "true";
    }

    @RequestMapping("/updatedatacart/{userid}")
    public String updateSteptCart(@PathVariable("userid") int userid) {

        logger.info(userid);

        cartRepo.setCheckoutstepAndUserid("complete", userid);
        return "true";
    }


    @RequestMapping("/createpdfdoc/{cartid}")
    public void getPdfDoc(@PathVariable("cartid") int cartid, HttpServletResponse response) throws IOException, DocumentException {
        logger.info("create pdf cartid" + cartid);


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        Cart cart = cartRepo.getById(cartid);

        response.setContentType("application/pdf");


        Document doc = new Document();
        PdfWriter.getInstance(doc, response.getOutputStream());
        // PdfWriter.getInstance(doc, response.set)
        doc.open();
        Font bold = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Paragraph paragraph = new Paragraph(currentPrincipalName);

        PdfPTable table = new PdfPTable(2);
        Stream.of("CityFrom CityTo", "").forEach(table::addCell);


        PdfPCell pdfPCell5;
       // PdfPCell pdfPCell6;

        pdfPCell5 = new PdfPCell(new Paragraph(cart.getCitiesname()));
        //pdfPCell6 = new PdfPCell(new Paragraph(cart.getCityTo()));
        table.addCell(pdfPCell5);
        //table.addCell(pdfPCell6);


        Stream.of("Distance", "Price").forEach(table::addCell);
        Stream.of(Integer.toString(cart.getDistance()) + "km", cart.getTotalprice() + "$").forEach(table::addCell);

        paragraph.add(table);
        doc.add(paragraph);

        doc.close();

    }
}
