package com.websocket.rest.api.service;

import java.util.List;

import com.websocket.rest.api.entity.ChatMessage;

public interface ChatMessageService {
	public ChatMessage save(ChatMessage chatMessage);

	public List<ChatMessage> findChatMessages(String senderId, String recipientId);
}
