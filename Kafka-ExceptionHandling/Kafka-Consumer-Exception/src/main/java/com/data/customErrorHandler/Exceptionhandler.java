package com.data.customErrorHandler;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.ConsumerAwareErrorHandler;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.util.backoff.FixedBackOff;

@Service("errordata")
public class Exceptionhandler implements ConsumerAwareListenerErrorHandler {

	private static final Logger log = LoggerFactory.getLogger(ConsumerAwareErrorHandler.class);
	
	@Override
	public Object handleError(Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer) {
		// TODO Auto-generated method stub
		log.info("GloablErrorHandler data :{} exceptionmessage:{}",message.toString(),exception.getMessage());
		return null;
	}

}
