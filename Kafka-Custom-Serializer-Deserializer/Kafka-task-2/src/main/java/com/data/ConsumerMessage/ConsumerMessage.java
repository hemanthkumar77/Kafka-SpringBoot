package com.data.ConsumerMessage;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.data.entity.Employer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ConsumerMessage {
   
	private static final Logger log = LoggerFactory.getLogger(ConsumerMessage.class);
	private ObjectMapper json = new ObjectMapper();
   
   @KafkaListener(topics="t_employerjson",concurrency="2")
   public void getmessage(String datarecords) throws JsonMappingException, JsonProcessingException {
	   Employer data = json.readValue(datarecords,Employer.class);
	   log.info("data recieved from the topic: "+ data.toString());
   }
}
