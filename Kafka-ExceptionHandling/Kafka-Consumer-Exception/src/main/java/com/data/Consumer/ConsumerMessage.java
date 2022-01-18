package com.data.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.data.customErrorHandler.Exceptionhandler;
import com.data.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//@Service
public class ConsumerMessage {

	private static final Logger log = LoggerFactory.getLogger(ConsumerMessage.class);
	
	// this one is straight forward configuration where inside the com.data.customErrorHandler package you have the ExceptionHandler class and you have the name has errordata 
	@KafkaListener(topics="t_emp",groupId="error",errorHandler="errordata")
	public void globalerror(String message) throws JsonMappingException, JsonProcessingException {
		ObjectMapper obj = new ObjectMapper();
		Employee instance = obj.readValue(message,Employee.class);
	   if(instance.getEmpSal() <60000.73) {
		throw new IllegalArgumentException("empId:"+instance.getEmpId()+"empName:"+instance.getEmpName()+"empSal:{}"+instance.getEmpSal());   
	   }
	   log.info("Successful employee message :{}",instance.toString());
	}
	
	
}
