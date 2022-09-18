package com.test.WebSocketPrac.nonStomp;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
    // 채팅방 조회 : 채팅방 Map에 담긴 정보를 조회
    // 채팅방 만들기 : 채팅방에 Random UUID로 ID를 부여한뒤 채팅방 Map에 추가
    // 메세지 발송 : 지정한 Websocket 세션에 메세지 발송

    private final ObjectMapper objectMapper;
    private Map<String, ChatRoomDto> chatRooms;
    @PostConstruct
    private void init(){
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoomDto> findAllRoom(){
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoomDto findRoomById(String roomId){
        return chatRooms.get(roomId);
    }

    public ChatRoomDto createRoom(String name){
        String id = UUID.randomUUID().toString();
        ChatRoomDto newChatRoom = ChatRoomDto.builder()
                .roomId(id)
                .name(name)
                .build();

        chatRooms.put(id, newChatRoom);
        return newChatRoom;
    }

    public <T> void sendMsg(WebSocketSession session, T msg){
        try{
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(msg)));
        } catch (IOException e){
            log.error(e.getMessage(),e);
        }
    }
}
