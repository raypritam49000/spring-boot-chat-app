package com.websocket.rest.api.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.websocket.rest.api.entity.ChatRoom;
import com.websocket.rest.api.repository.ChatRoomRepository;
import com.websocket.rest.api.service.ChatRoomService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

	private final ChatRoomRepository chatRoomRepository;

	@Override
	public Optional<String> getChatRoomId(String senderId, String recipientId, boolean createNewRoomIfNotExists) {
		return chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId).map(ChatRoom::getChatId)
				.or(() -> {
					if (createNewRoomIfNotExists) {
						var chatId = createChatId(senderId, recipientId);
						return Optional.of(chatId);
					}
					return Optional.empty();
				});
	}

	private String createChatId(String senderId, String recipientId) {
		var chatId = String.format("%s_%s", senderId, recipientId);

		ChatRoom senderRecipient = ChatRoom.builder().chatId(chatId).senderId(senderId).recipientId(recipientId)
				.build();

		ChatRoom recipientSender = ChatRoom.builder().chatId(chatId).senderId(recipientId).recipientId(senderId)
				.build();

		chatRoomRepository.save(senderRecipient);
		chatRoomRepository.save(recipientSender);

		return chatId;
	}

}
