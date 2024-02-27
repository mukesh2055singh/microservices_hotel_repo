package com.hotelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.kafkaService.KafkaService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/hotel")
public class HotelController {
	
	@Autowired HotelService hotelService;
	@Autowired KafkaService kafkaService;
	
	int count=0;
	@GetMapping("/{hotelId}")
	//@CircuitBreaker(name="hotelRatingBreaker",fallbackMethod="hotelRatingFallBack")
	//@Retry(name="hotelRatingService",fallbackMethod="hotelRatingFallBack")
	//@RateLimiter(name="hotelRatingRatelimiter",fallbackMethod="hotelRatingFallBack")
	public ResponseEntity<HotelBean> getRatingByUser(@PathVariable("hotelId") Integer hotelId){
		System.out.println("hotelId--:"+hotelId+":count--:"+count);
		HotelBean hotelBean=hotelService.getHotelRatingDetails(hotelId);
		System.out.println("hotelId--:"+hotelId+":count--:"+count);
		return ResponseEntity.ok(hotelBean);
	}
	
	public ResponseEntity<HotelBean> hotelRatingFallBack(Integer hotelId,Exception e){
		System.out.println("fallback method called--:"+hotelId+":::"+count);
		HotelBean hotelBean=new HotelBean();
		hotelBean.setHotelName("dummy hotel name");
		System.out.println("hotelId--:"+hotelId);
		return ResponseEntity.ok(hotelBean);
	}
	@GetMapping("/checkKafkaMsg")
	public String checkApacheKafkaMsg(){
		System.out.println("checkApacheKafkaMsg");
		kafkaService.checkRatingSend("4 rating");
		return "msg send";
	}
}
