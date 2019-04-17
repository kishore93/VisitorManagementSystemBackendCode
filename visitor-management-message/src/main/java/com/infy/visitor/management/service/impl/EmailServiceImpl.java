package com.infy.visitor.management.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.visitor.management.constant.ResponseMessageConstants;
import com.infy.visitor.management.entity.VisitorDetail;
import com.infy.visitor.management.service.EmailService;
import com.infy.visitor.management.service.MailService;
import com.infy.visitor.management.service.VelocityService;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private VelocityService velocityService;

	@Autowired
	private MailService mailService;

	private String sendNotification(VisitorDetail list) {
		String status = ResponseMessageConstants.INITIATED;
		String mailSubject = null;
		String mailBody = null;
		String mailto = null;
		String refferby = null;
		try {
				List<VisitorDetail> visitorList=new ArrayList<VisitorDetail>();
				visitorList.add(list);
			Map<String, Object> model = new HashMap<>();
			model.put("visitorList", visitorList);
			for (VisitorDetail visitors : visitorList) {

				if (mailto == null && visitors.getApproverEmail() != null) {
					mailto = visitors.getApproverEmail();
				}
				if (refferby == null && visitors.getReffererId()>0) {
					refferby = visitors.getReffererId()+"";
				}
			}
			model.put("refferby", refferby);
			mailSubject = velocityService.buildTemplate(model, "/templates/emailSubject.vm");
			mailBody = velocityService.buildTemplate(model, "/templates/emailBody.vm");

			List<String> toList = new ArrayList<String>();
			toList.add(mailto);
			mailService.sendEmail(toList, null, null, mailSubject, mailBody);
			status = ResponseMessageConstants.SUCCESS;
		} catch (Exception e) {
			status = ResponseMessageConstants.FAILED + ":" + mailto;
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String send(VisitorDetail obj) {
		if (obj != null ) {
			//if (obj.get(0) instanceof VisitorDetails) {
				String sendNotification = sendNotification(obj);
				return sendNotification;
			//}//

		}
		return null;
	}

}
