package com.data.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.data.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ConsumerRetry {
private static final Logger log = LoggerFactory.getLogger(ConsumerRetry.class);
	
	// Retry Mechanism configuration with global exception handler
	@KafkaListener(topics="t_emp",groupId="retrytestcase",containerFactory="RetryFactory")
	public void globalerror(String message) throws JsonMappingException, JsonProcessingException {
		ObjectMapper obj = new ObjectMapper();
		Employee instance = obj.readValue(message,Employee.class);
	   if(instance.getEmpSal() <60000.73) {
		throw new IllegalArgumentException("empId:"+instance.getEmpId()+"empName:"+instance.getEmpName()+"empSal:{}"+instance.getEmpSal());   
	   }
	   log.info("Successful employee message :{}",instance.toString());
	}
}
