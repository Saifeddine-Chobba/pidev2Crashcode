package tn.esprit.pidevcrashcode.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidevcrashcode.Entities.Complaint;
import tn.esprit.pidevcrashcode.Entities.Notification;
import tn.esprit.pidevcrashcode.Services.INotificationService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/notification")
public class NotificatonControleur {

    INotificationService iNotificationService;
    @GetMapping("/retriveAllnotification")
    public List<Notification> getnotification(){
        List<Notification> n=iNotificationService.retrieveAllNotification();
        return n;
    }
    @GetMapping("/retrivenotification/{notificationId}")
    public Notification retrivenotification(@PathVariable("notificationId")Integer notificationId){
        return iNotificationService.retrieveNotification(notificationId);
    }
    @PostMapping("/addnotification")
    public Notification addnotification(@RequestBody  Notification notification ){
        Notification n= iNotificationService.addNotification(notification);
        return n ;
    }
    @PutMapping("/updatenotification")
    public Notification updatenotification(@RequestBody Notification notification ){
        Notification updateNotification= iNotificationService.updateNotification(notification);
        return updateNotification ;
    }
    @DeleteMapping("/delatenotification/{notificationid}")
    public void  delatenotification (@PathVariable ("cnotificationid") Integer notificationid) {
        iNotificationService.deleteNotification(notificationid);
    }
    @PostMapping("/AddNotificationAndAssignToUser/{iduser}")
    public void AddNotificationAndAssignToUser(@RequestBody  Notification notification ,@PathVariable("iduser")int idUser){
        iNotificationService.AddNotificationAndAssignToUser(notification,idUser);
    }
}
