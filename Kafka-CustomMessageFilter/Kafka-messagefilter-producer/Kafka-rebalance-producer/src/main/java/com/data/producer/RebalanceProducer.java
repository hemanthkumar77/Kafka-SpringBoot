package com.data.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class RebalanceProducer {
 	
    private static final Logger log= LoggerFactory.getLogger(RebalanceProducer.class);
    private int i=0;
	@Autowired
	private KafkaTemplate kafatemplate;
	
	@Scheduled(fixedRate=3000)
	public void sendMessage() {
	 i++;
	 kafatemplate.send("t_rebalance","consumerRecords: "+i);
	 log.info("Kafka message details send successfully to the topic:{}","consumerRecords: "+i);
	}
}
