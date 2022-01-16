package com.data.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerData {

	private static final Logger log = LoggerFactory.getLogger(ConsumerData.class);
    
	@KafkaListener(topics="t_rebalance",concurrency="2")
	public void recieveData(ConsumerRecord<String,String> data){
		log.info("Consumer Partition:{} Consumer value:{} Consumer Offset:{}",data.partition(),data.value(),data.offset());
	}

}
	
