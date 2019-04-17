package com.infy.visitor.management.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.infy.visitor.management.constant.ResponseMessageConstants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProducerEmail {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String json) {
		log.info("Kafka Produce", json);
		Message<String> message = MessageBuilder.withPayload(json)
				.setHeader(KafkaHeaders.TOPIC, ResponseMessageConstants.TOPIC).build();
		this.kafkaTemplate.send(message);
	}
}
