package com.rating.service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rating.service.services.RatingService;
import com.rating.service.entity.Rating;
import com.rating.service.repository.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepository;
	
	@Override
	public Rating create(Rating rating) {
		// TODO Auto-generated method stub
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getAll() {
		return ratingRepository.findAll();
	}

	@Override
	public List<Rating> getRatingByUserId(String Id) {
		return ratingRepository.findByUserId(Id);
	}
	
	@Override
	public List<Rating> getRatingByHotelId(String Id) {
		return ratingRepository.findByHotelId(Id);
	}
}
