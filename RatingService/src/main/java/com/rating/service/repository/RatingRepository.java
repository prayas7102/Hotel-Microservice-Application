package com.rating.service.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rating.service.entity.Rating;

public interface RatingRepository extends MongoRepository<Rating, String> {
	List<Rating> findByUserId(String Id);

	List<Rating> findByHotelId(String Id);
}
