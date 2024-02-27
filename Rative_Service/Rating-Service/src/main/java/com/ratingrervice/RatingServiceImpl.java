package com.ratingrervice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {
	
	@Autowired private RatingRepository ratingRepository;
	
	@Override
	public RatingBean saveRating(RatingBean ratingBean) {
		// TODO Auto-generated method stub
		return ratingRepository.save(ratingBean);
	}

	@Override
	public List<RatingBean> getAllRating() {
		// TODO Auto-generated method stub
		return ratingRepository.findAll();
	}

	@Override
	public List<RatingBean> getRatingByUserId(String userId) {
		// TODO Auto-generated method stub
		return ratingRepository.findByUserId(userId);
	}

	@Override
	public List<RatingBean> getRatingByHotelId(String hotelId) {
		// TODO Auto-generated method stub
		return ratingRepository.findByUserId(hotelId);
	}

}
