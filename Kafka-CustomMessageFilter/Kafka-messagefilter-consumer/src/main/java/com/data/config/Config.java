package com.data.config;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
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
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;

import com.data.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class Config {
	@Autowired
	private KafkaProperties prop;
	
	private static final  Logger log = LoggerFactory.getLogger(Config.class);
	@Bean
	public ConsumerFactory<Object, Object> consumerFactory(){
		Map<String, Object> data=prop.buildConsumerProperties();
		data.put(ConsumerConfig.METADATA_MAX_AGE_CONFIG,"3000");
		return new DefaultKafkaConsumerFactory(data);
	}
	
	// this customfilter works in such way that the false conditions will be return to the kafkaListener which has the containerFactory="CustomFilter"
	//important point only the false condition will be returned , only the false 
	@Bean(name="CustomFilter")
	public ConcurrentKafkaListenerContainerFactory<Object, Object> datamethod(ConcurrentKafkaListenerContainerFactoryConfigurer config){
		ConcurrentKafkaListenerContainerFactory<Object, Object> details = new ConcurrentKafkaListenerContainerFactory<Object,Object>();
		config.configure(details, consumerFactory());
		details.setRecordFilterStrategy(new RecordFilterStrategy<Object,Object>(){
             
			ObjectMapper obj = new ObjectMapper();
			
			@Override
			public boolean filter(ConsumerRecord<Object, Object> consumerRecord) {
					// converting json string to emp object value
					Employee jsonValue;
					boolean value = false;
					try {
						jsonValue = obj.readValue(consumerRecord.value().toString(),Employee.class);
						value = jsonValue.getAge() < 70;
						log.info("Config class employer filter class :{} condition:{}",jsonValue.getAge(),value);
					} catch (JsonProcessingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();		
					}
					return value;
			}
		});
		
		
		return details;
		
	}
}
