package com.hotelService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HotelService {
	@Autowired HotelRepository hotelRepository;
	@Autowired RestTemplate restTemplate;
	
	public HotelBean getHotelRatingDetails(Integer hotelId) {
		HotelBean hotelBean = new HotelBean();
		try {
			hotelBean=hotelRepository.findByHotelId(hotelId);
			System.out.println("hotel service method");
			//String url = "http://localhost:8087/ratings/hotel/"+hotelId;
	        //List<RatingBean> ratingBeanList = restTemplate.getForObject(url,List.class);
	       // hotelBean.setRatingBeanList(ratingBeanList);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return hotelBean;
	}
}
