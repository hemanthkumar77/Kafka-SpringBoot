package com.data.CustomSerializerDeserializer;
import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomSerializer implements Serializer<Customer>{

    ObjectMapper object=new ObjectMapper();	
    
	@Override
	public byte[] serialize(String topic, Customer data) {
	 try {
		return object.writeValueAsBytes(data);
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	}


}
