package com.kafkaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
	@Autowired KafkaTemplate<String,String> kakaTemplate;
	
	public boolean checkRatingSend(String msg) {
		kakaTemplate.send("hotelMsg",msg);
		return true;
	}
}
