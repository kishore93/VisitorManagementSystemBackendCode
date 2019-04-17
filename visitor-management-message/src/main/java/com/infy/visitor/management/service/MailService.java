package com.infy.visitor.management.service;

import java.net.UnknownHostException;
import java.util.List;

import javax.mail.NoSuchProviderException;
import javax.mail.SendFailedException;
import javax.mail.internet.AddressException;

import com.infy.visitor.management.event.EmailNotificationEvent;

public interface MailService {

    void receiveEmailNotificationActionMessage(EmailNotificationEvent request);

    void sendEmail(List<String> toList, List<String> ccList, List<String> bccList, String subject, String body);

    void sendEmail(String fromAddress, List<String> toList, List<String> ccList, List<String> bccList, String subject, String body);

    void deliverEmail(EmailNotificationEvent event) throws NoSuchProviderException, SendFailedException, UnknownHostException, AddressException, java.security.NoSuchProviderException;

    void sendEmail(List<String> toList, List<String> ccList, List<String> bccList, String subject, String body, Boolean isCCList);

    void sendEmail(String fromAddress, List<String> toList, List<String> ccList, List<String> bccList, String subject, String body, Boolean isCCList);

}
