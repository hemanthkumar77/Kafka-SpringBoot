package com.data.CustomSerializerDeserializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.IntegerSerializer;

public class CustomerProducer {

	private String topicName="data-test";
	KafkaProducer<Integer,Customer> kafka;
	
	public CustomerProducer(Map<String,Object>maps) {
		kafka=new KafkaProducer<Integer,Customer>(maps);
	}
	
	public static Map<String,Object> config(){
		Map<String,Object> instance = new HashMap();
		instance.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092,localhost:9091,localhost:9093");
		instance.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,IntegerSerializer.class);
		instance.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,CustomSerializer.class);
		return instance;
	}
	
	public void dataRecords(int id,Customer record) {
	ProducerRecord<Integer, Customer> data=new ProducerRecord(topicName,id,record);
	try {
    RecordMetadata value=	kafka.send(data).get();
    System.out.println("data partion details: "+value.partition());
	}catch(Exception e){
		System.out.print("failed to push the records to the topic due to the following reasons: "+e.getMessage());
	}
	}
	
	public static void main(String args[]) {
		CustomerProducer instances = new CustomerProducer(config());
		List<Customer> details =List.of(new Customer(104,"DareDevil",1000,"PS5"),new Customer(105,"Batman",500,"PS4"),
				new Customer(106,"Superman",400,"Game-boy"));
		details.forEach(data->instances.dataRecords(data.getCustomerId(), data));
	}
}
