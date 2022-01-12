package com.data;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.data.producer.ProducerMessage;

@SpringBootApplication
//@EnableScheduling
public class KafkaProducerApplication implements CommandLineRunner {
    @Autowired
    private ProducerMessage message;	
	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
     		
		Map<String,String> data= new HashMap();
		data.put("DC","BATMAN");
		data.put("DC","Super-Man");
		data.put("MARVEL","ANTMAN");
		data.put("SONY","VENOM");
		data.put("MARVEL","ANTMAN");
	    data.forEach((key,value)->{
	      message.message(key, value);   	
	    });
	}

}
