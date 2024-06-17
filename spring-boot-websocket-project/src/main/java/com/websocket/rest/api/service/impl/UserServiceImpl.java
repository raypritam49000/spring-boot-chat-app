package com.websocket.rest.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.websocket.rest.api.entity.Status;
import com.websocket.rest.api.entity.User;
import com.websocket.rest.api.repository.UserRepository;
import com.websocket.rest.api.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public void savedUser(User user) {
		user.setStatus(Status.ONLINE);
		userRepository.save(user);
	}

	@Override
	public void disconnect(User user) {
		var storedUser = userRepository.findById(user.getNickName()).orElse(null);
		if (storedUser != null) {
			storedUser.setStatus(Status.OFFLINE);
			userRepository.save(storedUser);
		}
	}

	@Override
	public List<User> findConnectedUsers() {
		return userRepository.findAllByStatus(Status.ONLINE);
	}
}
