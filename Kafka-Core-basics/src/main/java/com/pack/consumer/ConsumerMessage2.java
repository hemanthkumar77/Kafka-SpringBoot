package com.pack.consumer;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class ConsumerMessage2 {
	 String topicName="test-data";
	   KafkaConsumer<String,String> kafka;
	   
	   public ConsumerMessage2(Map<String,Object>maps) {
		   kafka=new KafkaConsumer<String,String>(maps);
	   }
	   
	   public static Map<String,Object> method(){
		   Map<String,Object> instances=new HashMap();
		   instances.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092,localhost:9091,localhost:9093");
		   instances.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
		   instances.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
		   //instances.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
		   instances.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"false");
		   instances.put(ConsumerConfig.GROUP_ID_CONFIG,"test-group-test");
		   return instances;
	   }
	   
	   public void pollKafka() {
		   kafka.subscribe(List.of(topicName));
		   Duration instances=Duration.of(100,ChronoUnit.MILLIS);
		   try {
			   while(true) {
			  ConsumerRecords<String,String>details= kafka.poll(instances);
			  details.forEach((consumer)->{
				  System.out.println("Partition details: "+consumer.partition()+","+"Offset Details: "+consumer.offset());
				  System.out.println("Key: "+consumer.key()+","+"Value: "+consumer.value());
			  });
			  // so once all the records are processed the commit will happen so we have a full control over the commit 
			  if(details.count()>0) {
				  kafka.commitSync();
			  }
			   }
		   }catch(Exception e){
			   System.out.print("Failed to pull the records: "+e.getMessage());
		   }finally {
			   kafka.close();
		   }	      
	   }
	   
	   public static void main(String args[]) {
		ConsumerMessage2 con=new ConsumerMessage2(method());
		con.pollKafka();
	   }
}
