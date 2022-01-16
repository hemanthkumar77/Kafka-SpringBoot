package com.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KafkaMessagefilterProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaMessagefilterProducerApplication.class, args);
	}

}
 