package com.websocket.rest.api.service;

import java.util.List;

import com.websocket.rest.api.entity.User;


public interface UserService {

	public void savedUser(User user);

	public void disconnect(User user);

	public List<User> findConnectedUsers();

}
