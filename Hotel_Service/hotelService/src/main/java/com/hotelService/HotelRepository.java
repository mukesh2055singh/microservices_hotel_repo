package com.hotelService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<HotelBean,Integer> {
	HotelBean findByHotelId(Integer hotelId);
}
