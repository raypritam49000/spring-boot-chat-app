package com.websocket.rest.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.websocket.rest.api.entity.ChatMessage;
import com.websocket.rest.api.repository.ChatMessageRepository;
import com.websocket.rest.api.service.ChatMessageService;
import com.websocket.rest.api.service.ChatRoomService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

	private final ChatMessageRepository chatMessageRepository;
	private final ChatRoomService chatRoomService;

	@Override
	public ChatMessage save(ChatMessage chatMessage) {

		var chatId = chatRoomService.getChatRoomId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true)
				.orElseThrow();

		chatMessage.setChatId(chatId);

		chatMessageRepository.save(chatMessage);

		return chatMessage;
	}

	@Override
	public List<ChatMessage> findChatMessages(String senderId, String recipientId) {

		var chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);

		return chatId.map(chatMessageRepository::findByChatId).orElse(new ArrayList<>());
	}

}
