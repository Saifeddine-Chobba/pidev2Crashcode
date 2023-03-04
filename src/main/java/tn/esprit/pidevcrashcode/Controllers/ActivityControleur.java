package tn.esprit.pidevcrashcode.Controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidevcrashcode.Entities.Activity;
import tn.esprit.pidevcrashcode.Entities.Complaint;
import tn.esprit.pidevcrashcode.Services.IActivityService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Activity")
public class ActivityControleur {
    IActivityService iActivityService;

    @GetMapping("/retriveAllActivity")
    public List<Activity> getActivity(){
        List<Activity> activities=iActivityService.retrieveAllActivity();
        return activities;
    }
    @GetMapping("/retriveActivity/{ActivityId}")
    public Activity retriveActivity(@PathVariable("ActivityId")Integer ac){
        return iActivityService.retrieveActivity(ac);
    }
    @PostMapping("/addActivity")
    public Activity addActivity(@RequestBody Activity activity ){
        Activity  ac= iActivityService.addActivity(activity);
        return ac ;
    }
    @PutMapping("/updateActivity")
    public Activity updateActivity(@RequestBody Activity activity){
        Activity ac= iActivityService.updateActivity(activity);
        return ac;
    }
    @DeleteMapping("/delateActivity/{Activity}")
    public void  delateActivity (@PathVariable ("Activity") Integer activityId) {
        iActivityService.deleteActivity(activityId);
    }

    @PostMapping("/addActivityAndAssignToCamCenter/{idCampCenter}")
    public void addActivityAndAssignToCamCenter(@RequestBody  Activity activity , @PathVariable("idCampCenter")int idCampCenter){
        iActivityService.addActivityAndAssignToCamCenter(activity,idCampCenter);
    }
    @GetMapping("/RateActivityMoy/{ActivityId}")
    public float Moyenne(@PathVariable("ActivityId")int id){
        return iActivityService.AverageRating(id);
    }
    @PostMapping("/AddTofavorites/{idactivity}/{iduser}")
    public void AddTofavorites(@PathVariable("idactivity")int idActivity, @PathVariable("iduser") int idUser){
        iActivityService.AddTofavorites(idActivity,idUser);
    }

    @PutMapping("/delateFavoritActivities/{idactivity}/{iduser}")
    public void delateFavoritActivities(@PathVariable("idactivity")int idActivity, @PathVariable("iduser") int idUser){
        iActivityService.delateFavoritActivities(idActivity,idUser);
    }

    @GetMapping("/suggestActivities/{campcenterid}")
    public List<Activity> suggestActivities(@PathVariable("campcenterid")int idCampCenter){
        return iActivityService.suggestActivities(idCampCenter);
    }





}
