package com.test.WebSocketPrac.stomp;


import com.test.WebSocketPrac.nonStomp.MsgDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Msg {
    // Msg Type : 입장 , 대화
    public enum MsgType{
        ENTER, TALK
    }
    private MsgDto.MsgType type; // Msg Type
    private String roomId; // 방 Id
    private String sender; // 보낸 사람
    private String message; // 보낸 내용
}
