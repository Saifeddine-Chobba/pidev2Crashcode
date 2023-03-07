package tn.esprit.pidevcrashcode.Controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidevcrashcode.Entities.Chatroom;
import tn.esprit.pidevcrashcode.Services.I_ChatroomService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/chatroom")
public class ChatroomController {

    I_ChatroomService i_chatroomService;

    @PostMapping("/add-chatroom")
    public Chatroom addChatroom(@RequestBody Chatroom c){
        Chatroom Chatroom=i_chatroomService.CreateChatroom(c);
        return Chatroom;
    }
    @PostMapping("/update-Chatroom")
    public Chatroom modifyChatroom(@RequestBody Chatroom p){
        Chatroom Chatroom=i_chatroomService.UpdateChatroom(p);
        return Chatroom;
    }
    @GetMapping("/retrieve-all-Chatrooms")
    public List<Chatroom> getChatrooms()
    {
        List<Chatroom> L=i_chatroomService.RetriveAllChatrooms();
        return L;
    }
    @GetMapping("/retrieve-Chatroom/{chatroom-id}")
    public Chatroom getChatroom(@PathVariable("chatroom-id") int idC)
    {
        Chatroom chatroom =i_chatroomService.RetriveChatroom(idC);
        return chatroom;
    }

    @DeleteMapping("/delete-Chatroom/{chatroom-id}")
    public void deleteChatroom(@PathVariable("chatroom-id") int idC)
    {
        i_chatroomService.DeleteChatroom(idC);
    }

}



