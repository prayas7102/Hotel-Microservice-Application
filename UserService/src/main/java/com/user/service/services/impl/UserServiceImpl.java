package com.user.service.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.service.entity.Rating;
import com.user.service.entity.User;
import com.user.service.exceptions.ResourceNotFoundException;
import com.user.service.repository.UserRepository;
import com.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

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
			@SuppressWarnings("unchecked")
			ArrayList<Rating> ratingsOfUser = restTemplate
					.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), ArrayList.class);
			user.setRatings(ratingsOfUser);
		}
		return userList;
	}

	@Override
	public User getUser(String UserId) {
		User user = userRepository.findById(UserId)
				.orElseThrow(() -> new ResourceNotFoundException(UserId + " user not found"));

		@SuppressWarnings("unchecked")
		ArrayList<Rating> ratingsOfUser = restTemplate
				.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), ArrayList.class);
		user.setRatings(ratingsOfUser);
		return user;
	}

}
