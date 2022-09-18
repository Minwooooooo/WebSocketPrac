package com.test.WebSocketPrac.WebSocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.WebSocketPrac.nonStomp.ChatRoomDto;
import com.test.WebSocketPrac.nonStomp.ChatService;
import com.test.WebSocketPrac.nonStomp.MsgDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@Slf4j
@RequiredArgsConstructor
public class ChatHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
        String msg = message.getPayload();
        log.info("msg : {}", msg);
//        TextMessage textMessage = new TextMessage("아 아 채팅 테스트");
//        session.sendMessage(textMessage);
        MsgDto msgDto = objectMapper.readValue(msg, MsgDto.class);
        ChatRoomDto chatRoom = chatService.findRoomById(msgDto.getRoomId());
        chatRoom.handleAction(session,msgDto,chatService);

    }
}
