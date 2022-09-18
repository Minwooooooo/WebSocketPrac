package com.test.WebSocketPrac.stomp;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepo extends JpaRepository<ChatRoom, String> {
}
