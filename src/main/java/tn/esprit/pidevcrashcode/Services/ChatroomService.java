package tn.esprit.pidevcrashcode.Services;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidevcrashcode.Entities.Chatroom;
import tn.esprit.pidevcrashcode.Entities.User;
import tn.esprit.pidevcrashcode.Repositories.ChatroomRepository;


import java.util.List;

@Service
@NoArgsConstructor
public class ChatroomService implements I_ChatroomService {

    @Autowired
    ChatroomRepository chatroomRepository;

    public Chatroom CreateChatroom(Chatroom cr)
    {
        return chatroomRepository.save(cr);

    }

    public void DeleteChatroom(int idchat){

        chatroomRepository.deleteById(idchat);
    }
    public Chatroom UpdateChatroom(Chatroom chatroom){
        return chatroomRepository.save(chatroom);

    }
    public Chatroom RetriveChatroom(int  id)
    {
        return  chatroomRepository.findById(id).get();
    }
    public List<Chatroom> RetriveAllChatrooms()
    {
        return  chatroomRepository.findAll();
    }

    public boolean JoinChatroom(User U, Chatroom C)
    {
        List<User> userSet=C.getUsers();
     if (C.getUsers().size()<10){
         userSet.add(U);
         C.setUsers(userSet);
         chatroomRepository.save(C);
     }
     return (C.getUsers().size()<=10);
    }

    public  void ExitChatroom(User U, Chatroom C){
        List<User> userSet=C.getUsers();

        if(userSet.contains(U)){

            userSet.remove(U);
            C.setUsers(userSet);
            chatroomRepository.save(C);
        }


    }





}
