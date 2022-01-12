package com.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.data.consumer.ConsumerMessage;

@SpringBootApplication
public class KafkaConsumerApplication   {
    @Autowired
	private ConsumerMessage message;
	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}

}
