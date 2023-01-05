package de.hsa.OOSD.WebshopX.webshopx.services;

import de.hsa.OOSD.WebshopX.webshopx.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EmailService {
    private final String ART_X_MAIL_ADDRESS = "art.artx@gmx.de";
    private final String MAIL_SUBJECT = "ArtX - Vielen Dank für Deinen Einkauf";

    private String billContent;
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(ART_X_MAIL_ADDRESS);
        message.setTo(to);
        message.setSubject(MAIL_SUBJECT);
        message.setText(createSubject());

        mailSender.send(message);
    }

    public String setBillContent(Collection<Product> boughtItems, double sum){
        billContent = "";
        for (Product item : boughtItems){
            billContent += item.getName() + " - "+ item.getPrice() + " Euro\n";
        }
        billContent+="\nSumme: " + sum + " Euro";

        billContent+="\n\nBitte überweise " + sum + " Euro auf das folgende Konto:\n\n";
        billContent+="Empfänger: ArtX\nIBAN: 123456789\nBankinstitut: BankX\n\n";
        return billContent;
    }

    private String createSubject(){
        String start = "Lieber Kunde,\n\nvielen Dank für Deinen Einkauf. Anbei erhältst Du Deine Rechnung:\n\n";
        String bill = billContent;
        String end = "Mit freundlichen Grüßen\n\nDein ArtX-Team";
        return start + bill + end;
    }




}
