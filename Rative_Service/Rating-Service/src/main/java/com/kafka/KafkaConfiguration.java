package com.kafka;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import com.ratingrervice.RatingServiceImpl;

@Configuration
public class KafkaConfiguration{
	@Autowired RatingServiceImpl ratingServiceImpl;
	
	//@Transactional
	//@Modifying
	//@KafkaListener(topics ="hotelMsg",groupId="group-1")
    public void handleHotelBookEvent(String msg) {
        // Process the order placed event and update inventory
			/*RatingBean ratingBean=new RatingBean();
			ratingBean.setHotelId(hotelId);
			ratingBean.setUserId("1");
			ratingBean.setRating("5");
			ratingBean.setFeedback("Best");
			ratingServiceImpl.saveRating(ratingBean);*/
        System.out.println("Received message :"+msg);
    }
}
