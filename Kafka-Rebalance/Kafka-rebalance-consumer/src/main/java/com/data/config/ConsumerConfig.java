package com.data.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
public class ConsumerConfig {
    @Autowired
	private KafkaProperties kafak;
    
    @Bean
    public ConsumerFactory<String,String> consumerFactory(){
		  Map<String, Object> config =kafak.buildConsumerProperties();
    	  config.put(org.apache.kafka.clients.consumer.ConsumerConfig.METADATA_MAX_AGE_CONFIG,"4000");
    	  return new DefaultKafkaConsumerFactory(config);
    }
}
