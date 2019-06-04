package com.joshcummings.codeplay.terracotta.app.service

import javax.mail.Message

import com.joshcummings.codeplay.terracotta.email.gateway.EmailGateway
import com.joshcummings.codeplay.terracotta.service.EmailService

import spock.lang.Specification

class EmailServiceSpec extends Specification {

    EmailService emailService
    EmailGateway emailGateway

    def setup() {
        emailGateway = Mock(EmailGateway)
        emailService = new EmailService(emailGateway)
    }

    def "Email is properly sent"() {
        given:
            def recipient = "apanousis@gmail.com"
            def subject = "My Subject"
            def content = "My Content"

        when:
            def mailMessage = emailService.sendMessage(recipient, subject, content)

        then:
            mailMessage.getFrom()[0].toString() == emailService.from
            mailMessage.getRecipients(Message.RecipientType.TO)[0].toString() == recipient
            mailMessage.subject == subject
            mailMessage.content == content
            1 * emailGateway.sendEmail(_)

    }

}
