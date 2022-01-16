package com.data.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.data.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CosumerMessage {

	private static final Logger log = LoggerFactory.getLogger(CosumerMessage.class);
	
	// in this listener we applied the conatinerFactory custom bean name as CustomFilter were it will be take the false value and return us, not the true one.
	// so the false statement only we published , here we are set the age<70 in the filter but only the age greater then 70 will be shown 
	@KafkaListener(groupId="test1234",topics="t_messagefilter",containerFactory="CustomFilter")
	public void messager1(String records) {
		ObjectMapper obj = new ObjectMapper();
		try {
			Employee records1=obj.readValue(records, Employee.class);
			log.info("Senior Citizen Data publish :{}",records1.toString());
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	@KafkaListener(groupId="Citizen",topics="t_messagefilter")
	public void messager2(String records) {
		ObjectMapper obj = new ObjectMapper();
		try {
			Employee records1=obj.readValue(records, Employee.class);
			log.info("Citizen Data :{}",records1.toString());
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
