package com.infy.visitor.management.kafka;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.infy.visitor.management.constant.ResponseMessageConstants;
import com.infy.visitor.management.entity.VisitorDetail;
import com.infy.visitor.management.service.EmailService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConsumerEmail {
	
	@Autowired
	private EmailService emailService;


    @KafkaListener(topics = ResponseMessageConstants.TOPIC, groupId = "group_id")
    public void consume(String visitors) throws IOException {
        log.info(String.format("#### -> Consumed message -> %s", visitors));
        try {
        	VisitorDetail details= new Gson().fromJson(visitors, VisitorDetail.class);
        	System.out.println("EMail : "+emailService.send(details));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
