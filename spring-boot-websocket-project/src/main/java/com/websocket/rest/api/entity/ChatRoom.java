package com.websocket.rest.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
@Builder
public class ChatRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String chatId;
	private String senderId;
	private String recipientId;

}
