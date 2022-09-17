package com.test.WebSocketPrac;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MsgDto {
    // Msg Type : 입장 , 대화
    public enum MsgType{
        ENTER, TALK
    }
    private MsgType msgType; // Msg Type
    private String roomId; // 방 Id
    private String member; // 보낸 사람
    private String msg; // 보낸 내용
}
