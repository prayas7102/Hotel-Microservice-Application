package com.user.service.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.service.entity.Hotel;
import com.user.service.entity.Rating;
import com.user.service.entity.User;
import com.user.service.exceptions.ResourceNotFoundException;
import com.user.service.externalServices.ExternalServices;
import com.user.service.repository.UserRepository;
import com.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	ExternalServices externalServices;
	
	@Override
	public User saveUser(User user) {
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		List<User> userList = userRepository.findAll();
		for (User user : userList) {
			ResponseEntity<ArrayList<Rating>> responseEntity = restTemplate.exchange(
					"http://RATING-SERVICE/ratings/users/" + user.getUserId(), HttpMethod.GET, null,
					new ParameterizedTypeReference<ArrayList<Rating>>() {
					});

			ArrayList<Rating> ratingsOfUser = responseEntity.getBody();

			for (Rating rating : ratingsOfUser) {
//				Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(),
//						Hotel.class);
				Hotel hotel = externalServices.getHotel(rating.getHotelId());
				rating.setHotel(hotel);
			}
			user.setRatings(ratingsOfUser);
		}
		return userList;
	}

	@Override
	public User getUser(String UserId) {
		User user = userRepository.findById(UserId)
				.orElseThrow(() -> new ResourceNotFoundException(UserId + " user not found"));

		ResponseEntity<ArrayList<Rating>> responseEntity = restTemplate.exchange(
				"http://RATING-SERVICE/ratings/users/" + user.getUserId(), HttpMethod.GET, null,
				new ParameterizedTypeReference<ArrayList<Rating>>() {
				});

		ArrayList<Rating> ratingsOfUser = responseEntity.getBody();

		for (Rating rating : ratingsOfUser) {
//			Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
			Hotel hotel = externalServices.getHotel(rating.getHotelId());
			rating.setHotel(hotel);
		}
		user.setRatings(ratingsOfUser);
		return user;
	}

}
