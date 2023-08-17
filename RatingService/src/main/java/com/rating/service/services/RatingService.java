package com.rating.service.services;

import java.util.List;

import com.rating.service.entity.Rating;

public interface RatingService {
	Rating create(Rating rating);

	List<Rating> getAll();

	List<Rating> getRatingByHotelId(String Id);
	
	List<Rating> getRatingByUserId(String Id);
}
