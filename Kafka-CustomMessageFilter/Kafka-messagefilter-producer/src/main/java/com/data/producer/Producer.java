package com.data.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.data.entity.Employee;

@Service
public class Producer {
   
	private static final Logger log = LoggerFactory.getLogger(Producer.class);
	
	private int i;
	
	@Autowired
	private KafkaTemplate<String,String> kafka;
	
	public void messageProducer(Employee emp) throws JsonProcessingException {
		ObjectMapper obj = new ObjectMapper();
		String instance = obj.writeValueAsString(emp);
	    kafka.send("t_messagefilter", instance);
	    log.info("Record pushed to topic successfully:{}","emp_id: "+emp.getEmpId());
	}
    
	@Scheduled(fixedRate=3000)
    public void publishData() {
       int minAgeValue=20;
       int maxAgeValue=120;
       Employee emp1 = new Employee();
       Employee emp2 = new Employee();
       Employee emp3 = new Employee();
        
       emp1.setAge(maxAgeValue-i);
       emp2.setAge(minAgeValue+i);
       emp3.setAge(maxAgeValue+minAgeValue-i);
       
       emp1.setEmpId(100-i);
       emp2.setEmpId(200-i);
       emp3.setEmpId(300-i);
       
       try {
            messageProducer(emp1);
            messageProducer(emp2);
            messageProducer(emp3);
       }catch(Exception e) {
    	   log.info("While publishing data exception occured: "+e.getMessage());
       }
       i++;
    }
	
}
