package com.test.WebSocketPrac.stomp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/chat")
@Controller
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    // 채팅방 리스트
    @GetMapping("/room")
    public String rooms(Model model){
        System.out.println("채팅방 입장");
        return "/chat/room";
    }

    // 채팅방 목록
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> rooms(){
        return chatRoomService.findAllRoom();
    }

    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public ChatRoom creatRoom(@RequestParam String name){
        return chatRoomService.createChatRoom(name);
    }

    // 채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId){
        model.addAttribute("roomId",roomId);
        return "/chat/roomdetail";
    }

    // ID로 채팅방 찾기
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId){
        return chatRoomService.findRoomById(roomId);
    }
}
