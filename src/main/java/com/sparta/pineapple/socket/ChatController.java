package com.sparta.pineapple.socket;

import com.sparta.pineapple.dto.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    @PostMapping
    public ResponseDto<?> createRoom(HttpServletRequest request) {
        return chatService.createRoom(request);
    }

    @GetMapping
    public List<ChatRoom> findAllRoom() {
        return chatService.findAllRoom();
    }
}