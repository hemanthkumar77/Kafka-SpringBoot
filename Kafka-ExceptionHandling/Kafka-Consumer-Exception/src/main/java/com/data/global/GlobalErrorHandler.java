package com.data.global;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.ConsumerAwareErrorHandler;

public class GlobalErrorHandler implements ConsumerAwareErrorHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalErrorHandler.class);
	@Override
	public void handle(Exception thrownException, ConsumerRecord<?, ?> data, Consumer<?, ?> consumer) {
	log.info("data value:{} exception message:{}",data.value().toString(),thrownException.getMessage());
	}

}
