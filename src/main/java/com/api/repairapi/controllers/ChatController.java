package com.api.repairapi.controllers;

import com.api.repairapi.models.ChatModel;
import com.api.repairapi.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping
    public ArrayList<ChatModel> getChats(){
        return this.chatService.getChats();
    }

    @GetMapping(path = "/{id}")
    public Optional<ChatModel> getChatByid(@PathVariable Long id){
        return this.chatService.getChatById(id);
    }

    @PostMapping
    public ChatModel saveChat(@RequestBody ChatModel chat){
        return this.chatService.saveChat(chat);
    }

    @PutMapping(path = "/{id}")
    public ChatModel updateChat(@PathVariable Long id, @RequestBody ChatModel request){
        return this.chatService.updateChatById(request, id);
    }

    @DeleteMapping
    public void deleteChat(@PathVariable Long id){
        this.chatService.deleteChat(id);
    }

}
