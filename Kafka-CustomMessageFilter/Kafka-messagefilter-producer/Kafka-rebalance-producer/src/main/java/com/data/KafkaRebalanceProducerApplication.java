package com.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.data.producer.RebalanceProducer;

@SpringBootApplication
@EnableScheduling
public class KafkaRebalanceProducerApplication implements CommandLineRunner {
	@Autowired
	private RebalanceProducer producer;
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaRebalanceProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		producer.sendMessage();
	}

}
