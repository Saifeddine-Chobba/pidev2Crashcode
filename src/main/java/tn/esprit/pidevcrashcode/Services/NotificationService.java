package tn.esprit.pidevcrashcode.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidevcrashcode.Entities.Notification;
import tn.esprit.pidevcrashcode.Entities.User;
import tn.esprit.pidevcrashcode.Repositories.NotificationRepository;
import tn.esprit.pidevcrashcode.Repositories.UserRepository;

import java.util.List;
@Service
@Slf4j
@AllArgsConstructor
public class NotificationService implements INotificationService{
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    NotificationRepository notificationRepository;
    @Override
    public List<Notification> retrieveAllNotification() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification addNotification(Notification notification) {
        return  notificationRepository.save(notification);
    }

    @Override
    public void deleteNotification(Integer id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public Notification updateNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification retrieveNotification(Integer id) {
        return notificationRepository.findById(id).get();
    }

    @Override
    public void AddNotificationAndAssignToUser(Notification notification, int idUser) {
        User user=userRepository.findById(idUser).get();
        notification.setUser(user);
        notificationRepository.save(notification);
    }
}
