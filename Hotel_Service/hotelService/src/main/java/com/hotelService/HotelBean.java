/**
 * 
 */
package com.hotelService;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * @author mukesh.singh
 *
 */
@Entity
@Table(name="hotel_details")
public class HotelBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="hotel_id")
	Integer hotelId;
	@Column(name="hotel_name")
	String hotelName;
	@Column(name="user_id")
	String userId;
	@Column(name="status")
	Integer status;
	@Column(name="created_on")
	Date createdOn;
	
	@Transient
	List<RatingBean> ratingBeanList;
	public Integer getHotelId() {
		return hotelId;
	}
	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public List<RatingBean> getRatingBeanList() {
		return ratingBeanList;
	}
	public void setRatingBeanList(List<RatingBean> ratingBeanList) {
		this.ratingBeanList = ratingBeanList;
	}
}
