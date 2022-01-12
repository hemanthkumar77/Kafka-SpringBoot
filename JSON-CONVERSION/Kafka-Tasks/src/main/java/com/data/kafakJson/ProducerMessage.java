package com.data.kafakJson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.data.entity.Employer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProducerMessage {
	
	private static final Logger log =LoggerFactory.getLogger(ProducerMessage.class);
	
	@Autowired
	private KafkaTemplate<Integer,String>kafka;
	
	// to convert the message as json and sending the data to KAFKA
	private ObjectMapper json= new ObjectMapper();
	
	public void Message(Employer emp) throws JsonProcessingException {
		 kafka.send("t_employerjson",json.writeValueAsString(emp));
		 log.info("data pushed successfully to KAFKA Topic");
	}
}
