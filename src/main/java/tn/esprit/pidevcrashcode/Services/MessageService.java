package tn.esprit.pidevcrashcode.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pidevcrashcode.Entities.Message;
import tn.esprit.pidevcrashcode.Repositories.ChatroomRepository;
import tn.esprit.pidevcrashcode.Repositories.MessageRepository;
import tn.esprit.pidevcrashcode.Repositories.UserRepository;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class MessageService implements  I_MessageService{
        @Autowired
        MessageRepository messageRepository;
        @Autowired
        ChatroomRepository chatroomRepository;
        @Autowired
        UserRepository userRepository;
        @Override
        public Message CreateMessage(Message p)
        {
                return  messageRepository.save(p);
        }
        @Override
        public void DeleteMessage(int p)
        {
                messageRepository.deleteById(p);
        }
        @Override
        public Message UpdateMessage(Message p)
        {
                return  messageRepository.save(p);

        }
        @Override
        public Message RetriveMessage(int  id)
        {
                return  messageRepository.findById(id).get();
        }
        @Override
        public List<Message> RetriveAllMessages()
        {
                return  messageRepository.findAll();
        }

        @Override
        public void AddMessageAndAssignToUserAndChatroom(Message message, int idChatroom,int idUSer) {
                message.setUser(userRepository.findById(idUSer).get());
                message.setChatroom(chatroomRepository.findById(idChatroom).get());
                messageRepository.save(message);
        }

}
