package com.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.data.Producer.ProducerMessage;
import com.data.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootApplication
public class KafkaProducerEmployeeApplication implements CommandLineRunner {
	@Autowired
	private ProducerMessage message;
	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerEmployeeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
      List<Employee>emp = List.of(new Employee(100,"David-Patrick",55000.34),new Employee(101,"Watson-Shrinik",58000.88),
    		  new Employee(102,"willion-creek",65000.33),new Employee(103,"dono-manas",75000.34),
              new Employee(104,"Denrick-watts",85000.45),new Employee(105,"Jigsaw-Jhon",45000.34),
              new Employee(106,"Betty-Jelly",50000.34),new Employee(107,"Murdock-DareDevil",68000.44)
    		  );		
      emp.forEach(data->{
		try {
			message.messagePublish(data);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	});
	}

}
