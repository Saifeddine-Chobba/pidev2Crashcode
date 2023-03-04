package tn.esprit.pidevcrashcode.Services;

import tn.esprit.pidevcrashcode.Entities.Notification;

import java.util.List;

public interface INotificationService {
    List<Notification> retrieveAllNotification();

    Notification addNotification(Notification notification);

    void deleteNotification(Integer id);

    Notification updateNotification(Notification notification);

    Notification retrieveNotification(Integer id);

    void AddNotificationAndAssignToUser(Notification  notification,int idUser);

}
