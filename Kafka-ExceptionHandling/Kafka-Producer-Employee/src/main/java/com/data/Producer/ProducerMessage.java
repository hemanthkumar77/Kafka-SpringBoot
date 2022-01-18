package com.data.Producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.data.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProducerMessage {

	private static final Logger log = LoggerFactory.getLogger(ProducerMessage.class);
	@Autowired
	private KafkaTemplate kafka;
	
	public void messagePublish(Employee emp) throws JsonProcessingException {
		ObjectMapper obj = new ObjectMapper();
		String json = obj.writeValueAsString(emp);
		kafka.send("t_emp", json);
		log.info("Employee data are published to the topic:{}",emp.getEmpId());
	}
	
	
}
