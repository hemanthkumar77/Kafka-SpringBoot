package com.data.Producer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;
@Component
public class MessageProducer {
    String topicName="test-data";
    // KafkaProducer it is used to configured the properties like(server,key serializer,value serializer) and has the send() method to send the details
	KafkaProducer<String,String> kafak;
	
	public MessageProducer(Map<String,Object> maps){
		kafak = new KafkaProducer<String,String>(maps);
	}
	
	// creating a callback method for the async call for sending a message
	Callback callback =(data,exception)->{
		if(exception!=null) {
			System.out.print("failure on the kafka cluster with this message: "+exception.getMessage());
		}else {
			System.out.print("success fully data of the offset: "+data.offset()+","+"successfull partition details: "+data.partition());
		}
	};
	
	public static Map<String,Object> propsmap(){
		Map<String,Object> instance=new HashMap();
		instance.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092,localhost:9091,localhost:9093");
		instance.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
		instance.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
		instance.put(ProducerConfig.ACKS_CONFIG,"all");
		instance.put(ProducerConfig.RETRIES_CONFIG,10);
		instance.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG,50);
		return instance;	
	}
	
	// normal sync message to publish and check
	public void testMessage(String key,String value) throws InterruptedException, ExecutionException{
		// here we can send a message with the key and value pair
		ProducerRecord<String,String>instance= new ProducerRecord<>(topicName,key,value);
		// kafkaProducer send method will always returns the recordMetadata details 
		RecordMetadata instance1=kafak.send(instance).get();
        System.out.println("partition details: "+instance1.partition()+","+"Offset Details: "+instance1.offset());
	}
	
	public void testMessageAysnc(String key,String value) {
	ProducerRecord<String,String>instance=new ProducerRecord<>(topicName,key,value);
	kafak.send(instance, callback);
	}
	
	public static void main(String args[]) throws InterruptedException, ExecutionException {
	  MessageProducer object=new MessageProducer(propsmap());
	  object.testMessage("testdata","1234565");
	  
	  //while sending the async message in the kafka we need make sure to sleep the main thread for while because a new kafka thread is created and it will close a bit late so sleep the main thread for 3 sec
	 //object.testMessageAysnc("Test data","AYSNC-CALL");
	 //Thread.sleep(3000);
	}
}
