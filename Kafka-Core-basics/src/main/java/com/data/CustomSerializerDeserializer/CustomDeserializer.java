package com.data.CustomSerializerDeserializer;

import java.io.IOException;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomDeserializer implements Deserializer<Customer>{

	ObjectMapper object=new ObjectMapper();
	@Override
	public Customer deserialize(String topic, byte[] data) {
		// TODO Auto-generated method stub
		try {
			return object.readValue(data,Customer.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
