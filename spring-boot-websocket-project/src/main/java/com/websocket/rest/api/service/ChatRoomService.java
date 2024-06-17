package com.websocket.rest.api.service;

import java.util.Optional;

public interface ChatRoomService {

	public Optional<String> getChatRoomId(String senderId, String recipientId, boolean createNewRoomIfNotExists);

}
