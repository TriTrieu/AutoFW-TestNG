package com.bosch.utils;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;

import java.util.List;

public class EmailUtil {

    public static void sendTestReportViaEmail(List<String> recipients, String subject, String bodyMsg, String fileName) {
        try {
            // Create the attachment
            FileUtil file = new FileUtil();
            file.zipFile("bien moi truong" + "/output", fileName);
            EmailAttachment attachment = new EmailAttachment();
            attachment.setPath("bien moi truong" + "\\" + fileName);
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setDescription("Automation test report");
            attachment.setName(fileName);

            // Create the email message
            MultiPartEmail email = new MultiPartEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setSslSmtpPort(String.valueOf(465));
            email.setAuthenticator(new DefaultAuthenticator("giaiphaptritin@gmail.com", "giaiphaptritin"));
            email.setSSLOnConnect(true);
            email.setFrom("giaiphaptritin@gmail.com", "4SV");

            for (String recipient : recipients) {
                email.addTo(recipient);
            }
            email.setSubject(subject);
            email.setMsg(bodyMsg);

            // add the attachment
            email.attach(attachment);

            // send the email
            email.send();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
