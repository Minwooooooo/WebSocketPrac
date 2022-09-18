package com.test.WebSocketPrac.stomp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepo chatRoomRepo;

    public List<ChatRoom> findAllRoom(){
        List<ChatRoom> allChatRooms = chatRoomRepo.findAll();
        return allChatRooms;
    }

    public ChatRoom findRoomById(String roomId){
        ChatRoom chatRoom = chatRoomRepo.findById(roomId).get();
        return chatRoom;
    }

    public ChatRoom createChatRoom(String roomName){
        ChatRoom chatRoom = ChatRoom.createRoom(roomName);
        chatRoomRepo.save(chatRoom);
        return chatRoom;
    }
}
