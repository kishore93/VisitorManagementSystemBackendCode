package com.infy.visitor.management.service.impl;

import java.net.UnknownHostException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.infy.visitor.management.event.EmailNotificationEvent;
import com.infy.visitor.management.service.MailService;
import com.infy.visitor.management.utils.Utility;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	String fromAddress;


	public void receiveEmailNotificationActionMessage(EmailNotificationEvent request) {
		try {
			Set<String> toSet = new HashSet<>();
			Set<String> ccSet = new HashSet<>();
			if (!CollectionUtils.isEmpty(request.getToList())) {
				for (String toEmail : request.getToList()) {
					if (!StringUtils.isEmpty(toEmail)) {
						if (toEmail.contains(";")) {
							String toEmailArray[] = toEmail.split("[;]");
							for (String splitEmail : toEmailArray) {
								if (!StringUtils.isEmpty(splitEmail))
									toSet.add(splitEmail);
							}
						} else {
							toSet.add(toEmail);
						}
					}
				}
			}
			if (null == request.getIsCCList() || request.getIsCCList() == Boolean.TRUE) {
				if (!CollectionUtils.isEmpty(request.getCcList()))
					ccSet.addAll(request.getCcList());

			}

			List<String> toList = new ArrayList<>(toSet);
			List<String> ccList = new ArrayList<>(ccSet);
			request.setToList(toList);
			request.setCcList(ccList);
			request.setFromAddress(
					null != request.getFromAddress() ? request.getFromAddress() : fromAddress);
			deliverEmail(request);

		} catch (NoSuchProviderException | SendFailedException | UnknownHostException e) {
			log.error("Exception occurred in receiveAlertsNotificationActionMessage()" + e.getMessage());

		} catch (AddressException ae) {

			log.error("Exception occurred in receiveAlertsNotificationActionMessage() Invalid address -"
					+ ae.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occurred in receiveAlertsNotificationActionMessage() " + e.getMessage());
		}

	}

	@Override
	public synchronized void sendEmail(List<String> toList, List<String> ccList, List<String> bccList, String subject,
			String body) {
		sendEmail(null, toList, ccList, bccList, subject, body);
	}

	@Override
	public synchronized void sendEmail(List<String> toList, List<String> ccList, List<String> bccList, String subject,
			String body, Boolean isCCList) {
		sendEmail(null, toList, ccList, bccList, subject, body, isCCList);
	}

	@Override
	public synchronized void sendEmail(String fromAddress, List<String> toList, List<String> ccList,
			List<String> bccList, String subject, String body) {
		EmailNotificationEvent event = EmailNotificationEvent.builder().toList(toList).ccList(ccList).body(body)
				.subject(subject).fromAddress(fromAddress).build();
		receiveEmailNotificationActionMessage(event);


	}

	@Override
	public synchronized void sendEmail(String fromAddress, List<String> toList, List<String> ccList,
			List<String> bccList, String subject, String body, Boolean isCCList) {
		try {
			EmailNotificationEvent event = EmailNotificationEvent.builder().toList(toList).ccList(ccList).body(body)
					.subject(subject).fromAddress(fromAddress).build();
			receiveEmailNotificationActionMessage(event);

		} catch (Exception exception) {
			log.error("Exception occurs while sending an email", exception);
		}

	}

	public boolean isValidEmailAddress(@SuppressWarnings("rawtypes") List emailList) throws Exception {
		boolean result = false;
		InternetAddress emailAddress = null;
		try {
			if (!CollectionUtils.isEmpty(emailList)) {
				for (Object email : emailList) {
					if (!StringUtils.isEmpty(email)) {
						emailAddress = new InternetAddress(email.toString());
						emailAddress.validate();
						result = true;
					}
				}
			}
		} catch (AddressException | NullPointerException e) {
			log.error("Enter Valid Email Address", e.getMessage());
			throw new AddressException(e.getMessage());
		}
		return result;
	}

	@Override
	public void deliverEmail(EmailNotificationEvent event)
			throws NoSuchProviderException, SendFailedException, UnknownHostException, AddressException {
		MimeMessage message = javaMailSender.createMimeMessage();

		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

			if (Utility.isNotEmpty(event.getToList()) && (isValidEmailAddress(event.getToList()))) {
				Set<String> uniqueIds = new HashSet<>(event.getToList());
				String[] toArray = uniqueIds.toArray(new String[uniqueIds.size()]);
				// for (String to : uniqueIds) {
				messageHelper.setTo(toArray);
				// }
			} else {
				log.error("Enter Valid Email Address");
				throw new AddressException();
			}
			// CC list
			if (Utility.isNotEmpty(event.getCcList()) && (isValidEmailAddress(event.getCcList()))) {
				Set<String> uniqueIds = new HashSet<>(event.getCcList());
				String[] ccArray = uniqueIds.toArray(new String[uniqueIds.size()]);
				// for (String cc : uniqueIds) {
				messageHelper.setCc(ccArray);
				// }
			}

			// Subject
			if (Utility.isNotEmpty(event.getSubject()))
				messageHelper.setSubject(event.getSubject());

			// From Address get this from the application.yml files
			if (Utility.isNotEmpty(event.getFromAddress()))
				messageHelper.setFrom(event.getFromAddress());

			// Body
			if (Utility.isNotEmpty(event.getBody()))
				messageHelper.setText(event.getBody(), true);

			javaMailSender.send(message);

		} catch (NoSuchProviderException nspe) {
			throw new NoSuchProviderException(nspe.getMessage());
		} catch (SendFailedException sfe) {
			throw new SendFailedException(sfe.getMessage());
		} catch (UnknownHostException ue) {
			log.error("Un knowHost Exception Exception occurs while sending an email", ue);
			throw new UnknownHostException(ue.getMessage());
		} catch (AddressException ae) {
			log.error("Exception occurs while sending an email", ae);
			throw new AddressException(ae.getMessage());
		} catch (MailSendException | MessagingException me) {
			log.error("Exception occurs while sending an email", me);
			if (me.getCause() != null && me.getCause().getCause() instanceof UnknownHostException) {
				throw new UnknownHostException(me.getMessage());
			} else {
				throw new MailSendException(me.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occurs while sending an email", e);
			// throw new MailSendException(e.getMessage());
		}
	}

}
