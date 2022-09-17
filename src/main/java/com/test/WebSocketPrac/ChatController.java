package com.test.WebSocketPrac;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ChatController {
    private final ChatService chatService;

    @PostMapping(value = "/chat")
    public ChatRoomDto createRoom(@RequestBody String roomName){
        return chatService.createRoom(roomName);
    }

    @GetMapping(value = "/chat")
    public List<ChatRoomDto> roomsList(){
        return chatService.findAllRoom();
    }
}
