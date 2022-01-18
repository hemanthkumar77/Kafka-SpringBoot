package com.data.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.data.entity.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Service
public class ConsumerMessageDeadLetter {
private static final Logger log = LoggerFactory.getLogger(ConsumerMessageDeadLetter.class);
	
    // here the containerFactory refers to the bean name we configured in the config class
    // if the emp salary is less than 60000.73 it will publish only to the deadlettertopic what we created and configured in the bean DeadMessageProcessing
	@KafkaListener(topics="t_emp",containerFactory="DeadMessageProcessing",groupId="new-data")
	public void messageReciever(String message) throws JsonMappingException, JsonProcessingException {
		ObjectMapper obj = new ObjectMapper();
		Employee instance = obj.readValue(message,Employee.class);
	   if(instance.getEmpSal() <60000.73) {
		throw new IllegalArgumentException("empId:"+instance.getEmpId()+"empName:"+instance.getEmpName()+"empSal:{}"+instance.getEmpSal());   
	   }
	   log.info("Successful employee message :{}",instance.toString());
	}
}
