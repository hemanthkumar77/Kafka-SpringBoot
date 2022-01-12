package com.data.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ProducerMessage {
    @Autowired
	private KafkaTemplate<String,String>kafka;
	
   private static final Logger log= LoggerFactory.getLogger(ProducerMessage.class);
  
  // @Scheduled(fixedRate= 1000)
	public void message(String key,String name) {
		kafka.send("test-distribution",key,name);
		log.info("producer data: {}"+name);
	}
}
