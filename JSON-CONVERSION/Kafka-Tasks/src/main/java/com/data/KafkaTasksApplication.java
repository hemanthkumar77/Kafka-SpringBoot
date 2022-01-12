package com.data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.data.entity.Employer;
import com.data.kafakJson.ProducerMessage;
import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootApplication
public class KafkaTasksApplication implements CommandLineRunner {
	@Autowired
	private ProducerMessage message;
	public static void main(String[] args) {
		SpringApplication.run(KafkaTasksApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
	   List<Employer> emp= List.of(new Employer(100,"BATMAN",LocalDate.now()),new Employer(101,"DareDevil",LocalDate.now()),
			   new Employer(102,"SuperMan",LocalDate.now()),new Employer(103,"SuperWoman",LocalDate.now()));
	   emp.forEach(list->{
		try {
			message.Message(list);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	});
	}

	
}
