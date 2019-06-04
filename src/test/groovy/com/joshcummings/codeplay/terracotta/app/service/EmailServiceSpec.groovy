package com.joshcummings.codeplay.terracotta.app.service

import javax.mail.Message
import javax.mail.Transport

import com.joshcummings.codeplay.terracotta.service.EmailService

import spock.lang.Specification
import spock.util.mop.ConfineMetaClassChanges

@ConfineMetaClassChanges([Transport])
class EmailServiceSpec extends Specification {

    EmailService emailService

    def setup() {
        emailService = new EmailService()
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
//            Transport.desiredAssertionStatus()
    }

}
