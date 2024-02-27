package com.ratingrervice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratings")
public class RatingController {
	
	@Autowired RatingServiceImpl ratingServiceImpl;
	
	@PostMapping
	public ResponseEntity<RatingBean> createRating(@RequestBody RatingBean ratingBean){
		ratingBean=ratingServiceImpl.saveRating(ratingBean);
		return ResponseEntity.status(HttpStatus.CREATED).body(ratingBean);
	}
	@GetMapping("user/{userId}")
	public ResponseEntity<List<RatingBean>> getRatingByUser(@PathVariable String userId){
		List<RatingBean> ratingBeanList=ratingServiceImpl.getRatingByUserId(userId);
			return ResponseEntity.ok(ratingBeanList);
	}
	@GetMapping("hotel/{hotelId}")
	public List<RatingBean> getRatingByHotel(@PathVariable String hotelId){
		List<RatingBean> ratingBeanList=ratingServiceImpl.getRatingByHotelId(hotelId);
		System.out.println("rating servidce mapping called");	
		return ratingBeanList;
	}
	@GetMapping
	public ResponseEntity<List<RatingBean>> getAllRating(){
		List<RatingBean> allRatingList=ratingServiceImpl.getAllRating();
		 return ResponseEntity.ok(allRatingList);
	}
}
