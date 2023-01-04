package de.hsa.OOSD.WebshopX.webshopx.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final String ART_X_MAIL_ADDRESS = "art.artx@gmx.de";
    private final String MAIL_SUBJECT = "ArtX - Vielen Dank für Deinen Einkauf";
    private final String MAIL_BODY = "Lieber Kunde,\n\nvielen Dank dass Du Teil der ArtX Community bist. Wir haben den/die Verkäufer Deines Einkaufs kontaktiert. Du wirst in Kürze Deine Rechnung und anschließend Dein(e) Gemälde erhalten.\n\n Dein ArtX Team";
    @Autowired
    private JavaMailSender mailSender;


    public void sendEmail(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(ART_X_MAIL_ADDRESS);
        message.setTo(to);
        message.setSubject(MAIL_SUBJECT);
        message.setText(MAIL_BODY);

        mailSender.send(message);
    }
}
