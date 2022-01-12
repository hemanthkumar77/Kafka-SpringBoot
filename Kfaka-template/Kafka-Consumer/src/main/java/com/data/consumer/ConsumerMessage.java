package com.data.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Service
public class ConsumerMessage {

	private static final Logger log = LoggerFactory.getLogger(ConsumerMessage.class);
	
	@KafkaListener(topics="test-distribution",concurrency="4")
	public void message(ConsumerRecord<String,String> records) {
		System.out.println("Key value: "+records.key()+"value: "+records.value());
		log.info("Key:{} Value:{}",records.key(),records.value());
	}
}
