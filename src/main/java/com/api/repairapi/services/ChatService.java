package com.api.repairapi.services;

import com.api.repairapi.models.ChatModel;
import com.api.repairapi.repositories.IChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ChatService {

    @Autowired
    private IChatRepository chatRepository;

    public ArrayList<ChatModel> getChats(){
        return (ArrayList<ChatModel>) this.chatRepository.findAll();
    }

    public Optional<ChatModel> getChatById(Long id){
        return this.chatRepository.findById(id);
    }

    public ChatModel saveChat(ChatModel chat){
        return this.chatRepository.save(chat);
    }

    public ChatModel updateChatById(ChatModel request, Long id){
        ChatModel chat = this.chatRepository.findById(id).get();

        chat.setClient(request.getClient());
        chat.setTechnician(request.getTechnician());
        chat.setMessage(request.getMessage());
        chat.setDate(request.getDate());

        return  this.chatRepository.save(chat);
    }

    public void deleteChat(Long id){
        try {
            this.chatRepository.deleteById(id);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
