package com.test.WebSocketPrac.stomp;



import com.test.WebSocketPrac.nonStomp.MsgDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final SimpMessageSendingOperations messageSendingOperations;

    @MessageMapping("/chat/message")
    public void msg(Msg msg){
        if (MsgDto.MsgType.ENTER.equals(msg.getType())){
            msg.setMessage(msg.getSender()+"님이 입장하셨습니다.");
//            System.out.println(msg.getRoomId());
//            System.out.println(msg.getSender()+"님이 입장하셨습니다.");
        }
        System.out.println("-----------------");
        System.out.println(msg.getRoomId());
        System.out.println(msg.getSender());
        System.out.println(msg.getMessage());
        System.out.println(msg.getType().toString());
        System.out.println("-----------------");

        messageSendingOperations.convertAndSend("/sub/chat/room/"+msg.getRoomId(),msg);
    }
}
