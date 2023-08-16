package com.user.service.services;

import java.util.List;
import java.util.Optional;

import com.user.service.entity.User;

public interface UserService {
	User saveUser(User user);

	List<User> getAllUser();

	User getUser(String UserId);
}
