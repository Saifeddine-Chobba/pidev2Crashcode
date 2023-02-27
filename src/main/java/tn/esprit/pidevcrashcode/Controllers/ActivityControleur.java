package tn.esprit.pidevcrashcode.Controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidevcrashcode.Entities.Activity;
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
}
