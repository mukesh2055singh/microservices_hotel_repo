package com.ratingrervice;

import java.util.List;

public interface RatingService {
	public RatingBean saveRating(RatingBean userBean);
	public List<RatingBean> getAllRating();
	public List<RatingBean> getRatingByUserId(String userId);
	public List<RatingBean> getRatingByHotelId(String hotelId);
}
