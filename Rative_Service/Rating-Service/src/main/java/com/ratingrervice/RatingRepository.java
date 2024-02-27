package com.ratingrervice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<RatingBean,Integer> {
	public List<RatingBean> findByUserId(String userId);
	public List<RatingBean> findByHotelId(String hotelId);
}
