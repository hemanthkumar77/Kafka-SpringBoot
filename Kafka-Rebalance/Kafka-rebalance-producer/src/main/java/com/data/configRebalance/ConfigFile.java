package com.data.configRebalance;

import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class ConfigFile {
	@Autowired
    private KafkaProperties kafak;
	
	@Bean
	public ProducerFactory<String,String> consumerFactory(){
		Map<String, Object> config = kafak.buildProducerProperties();
		config.put(ProducerConfig.METADATA_MAX_AGE_CONFIG,"3000");
		return new DefaultKafkaProducerFactory(config);
	}
	
	@Bean
	public KafkaTemplate<String,String> kafkatemplate(){
		return new KafkaTemplate(consumerFactory()); 
	}
	
}
