package com.data.config;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.util.backoff.FixedBackOff;
import com.data.customErrorHandler.*;
import com.data.entity.Employee;
import com.data.global.GlobalErrorHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@Configuration
public class Config {
	@Autowired
	private KafkaProperties prop;
	
	private static final  Logger log = LoggerFactory.getLogger(Config.class);
	@Bean
	public ConsumerFactory<Object, Object> consumerFactory(){
		Map<String, Object> data=prop.buildConsumerProperties();
		data.put(ConsumerConfig.METADATA_MAX_AGE_CONFIG,"1000");
		return new DefaultKafkaConsumerFactory(data);
	}
	
	// this bean is configured in the consumermessageDeadLetter service class.
	@Bean(name="DeadMessageProcessing")
	public ConcurrentKafkaListenerContainerFactory<Object, Object> datamethod(ConcurrentKafkaListenerContainerFactoryConfigurer config,KafkaOperations<String,String> kafka){
		ConcurrentKafkaListenerContainerFactory<Object, Object> details = new ConcurrentKafkaListenerContainerFactory<Object,Object>();
		config.configure(details, consumerFactory());
		
		// here we are configuring the DeadLetterPublishingRecoverer such that the failed records are being processed to the new topic and checking whether that topic has the same partition count 
		DeadLetterPublishingRecoverer recover = new DeadLetterPublishingRecoverer(kafka,(record,ex)->new TopicPartition("t_emp_dlt",record.partition()));
		// so here it will make sure that the failed messages will be consider has the pending messages needs to be processed 
		// here we are using the retry mechanism to 5 retry, 10 second interval for each retry
		//SeekToCurrentErrorHandler data = new SeekToCurrentErrorHandler(recover, new FixedBackOff(10_000, 5));
        details.setErrorHandler(new GlobalErrorHandler());
        details.setRetryTemplate(template());
        return details;
	}
		
	private RetryTemplate template() {
		RetryTemplate retry = new RetryTemplate();
        // call the particular topic for three times if the message is failed		
		SimpleRetryPolicy instances = new SimpleRetryPolicy(3);
		retry.setRetryPolicy(instances);
		// in the interval of 5 secs for each times
		FixedBackOffPolicy value = new FixedBackOffPolicy();
		value.setBackOffPeriod(5000);
		retry.setBackOffPolicy(value);
		return retry;
		
	}
	
	// retry mechanism for the kafka we added.
	@Bean(value = "RetryFactory")
	public ConcurrentKafkaListenerContainerFactory<Object, Object> retryMechanism(ConcurrentKafkaListenerContainerFactoryConfigurer config){
		ConcurrentKafkaListenerContainerFactory<Object, Object> details = new ConcurrentKafkaListenerContainerFactory<Object,Object>();
		config.configure(details, consumerFactory());
        details.setErrorHandler(new GlobalErrorHandler());
        details.setRetryTemplate(template());
        return details;
	}
		
	
}
