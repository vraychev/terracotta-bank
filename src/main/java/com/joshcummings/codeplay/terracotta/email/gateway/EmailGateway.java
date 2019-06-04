package com.joshcummings.codeplay.terracotta.email.gateway;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

public class EmailGateway {
    public void sendEmail(MimeMessage emailMessage) throws MessagingException {
        Transport.send(emailMessage);
    }
}
