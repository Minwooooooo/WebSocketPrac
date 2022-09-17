package com.test.WebSocketPrac;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ChatRoomDto {
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoomDto(String roomId, String name){
        this.roomId = roomId;
        this.name = name;
    }

    public void handleAction(WebSocketSession session, MsgDto msgDto, ChatService chatService){
        if(msgDto.getMsgType().equals(MsgDto.MsgType.ENTER)){
            sessions.add(session);
            msgDto.setMsg(msgDto.getMember()+"님이 입장하셨습니다.");
        }
        sendMsg(msgDto,chatService);
    }

    public <T> void sendMsg(T msg, ChatService chatService){
        sessions.parallelStream().forEach(session -> chatService.sendMsg(session,msg));
    }
}
