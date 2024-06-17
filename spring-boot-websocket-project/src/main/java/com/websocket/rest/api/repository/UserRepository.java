package com.websocket.rest.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.websocket.rest.api.entity.Status;
import com.websocket.rest.api.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	List<User> findAllByStatus(Status status);
}
