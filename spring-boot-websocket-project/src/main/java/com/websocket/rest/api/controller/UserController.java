package com.websocket.rest.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.websocket.rest.api.entity.User;
import com.websocket.rest.api.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@MessageMapping("/user.addUser")
	@SendTo("/user/topic")
	public User addUser(@Payload User user) {
		userService.savedUser(user);
		return user;
	}

	@MessageMapping("/user.disconnect")
	@SendTo("/user/topic")
	public User disconnect(@Payload User user) {
		userService.disconnect(user);
		return user;
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> findConnectedUsers() {
		return new ResponseEntity<List<User>>(userService.findConnectedUsers(), HttpStatus.OK);
	}
}
