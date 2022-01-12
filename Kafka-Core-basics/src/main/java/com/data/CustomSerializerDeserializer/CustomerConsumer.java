package com.data.CustomSerializerDeserializer;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;


public class CustomerConsumer {
   private String topicName="data-test";
   KafkaConsumer<Integer,Customer> kafka;
   
   public CustomerConsumer(Map<String,Object>maps) {
	   kafka = new KafkaConsumer<Integer,Customer>(maps);
   }
   
   public static Map<String,Object> config(){
	   Map<String,Object> instances=new HashMap();
	   instances.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092,localhost:9091,localhost:9093");
	   instances.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,IntegerDeserializer.class);
	   instances.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,CustomDeserializer.class);
	   instances.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
	   instances.put(ConsumerConfig.GROUP_ID_CONFIG,"new-group");
	   return instances;
   }
   
   public void message(){
	kafka.subscribe(List.of(topicName));
	try {
		while(true) {
		Duration instance = Duration.of(100,ChronoUnit.MILLIS);
      ConsumerRecords<Integer, Customer> data =kafka.poll(instance);	
      data.forEach((customer)->{
    	  System.out.println("customerId: "+customer.key()+","+"customerDetails: "+customer.value());
      });}
	}catch(Exception e) {
		System.out.print("");
	}
   }
   
   public static void main(String args[]) {
	   CustomerConsumer object = new CustomerConsumer(config());
	   object.message();
   }
}
