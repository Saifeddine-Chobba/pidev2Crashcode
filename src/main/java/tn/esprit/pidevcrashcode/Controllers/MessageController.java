package tn.esprit.pidevcrashcode.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidevcrashcode.Entities.Message;
import tn.esprit.pidevcrashcode.Services.I_MessageService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/message")
public class MessageController {

    I_MessageService i_messageService;

    @PostMapping("/add-message")
    public Message addMessage(@RequestBody Message r){
        Message message=i_messageService.CreateMessage(r);
        return message;
    }

    @PostMapping("/update-message")
    public Message modifyMessage(@RequestBody  Message r){
        Message message=i_messageService.UpdateMessage(r);
        return message;
    }

    @GetMapping("/retrieve-all-messages")
    public List<Message> getMessages()
    {
        List<Message> L=i_messageService.RetriveAllMessages();
        return L;
    }

    @GetMapping("/retrieve-message/{message-id}")
    public Message getMessage(@PathVariable("message-id") int idMess)
    {
        Message message =i_messageService.RetriveMessage(idMess);
        return message;
    }

    @DeleteMapping("/delete-message/{message-id}")
    public void deleteMessage(@PathVariable("message-id") int idMess)
    {
        i_messageService.DeleteMessage(idMess);
    }
    @PostMapping("/add-message-assign/{chatroom-id}/{user-id}")
    public Message AddAndAssignMessage(@RequestBody  Message message,@PathVariable("chatroom-id") int idCaht,@PathVariable("user-id") int idUser){
        i_messageService.AddMessageAndAssignToUserAndChatroom(message,idCaht,idUser);
        return message;
    }

}



