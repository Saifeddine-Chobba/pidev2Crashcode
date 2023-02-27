package tn.esprit.pidevcrashcode.Controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidevcrashcode.Entities.ActivityRating;
import tn.esprit.pidevcrashcode.Services.IActivityRatingService;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/ActivityRate")
public class AtivityRateControleur {
    IActivityRatingService iActivityRatingService;
    @GetMapping("/retriveAllComplaint")
    public List<ActivityRating> getActivityRating(){
        List<ActivityRating> ar=iActivityRatingService.retrieveAllActivityRating();
        return ar;
    }
    @GetMapping("/retriveActivityRating/{ActivityRateId}")
    public ActivityRating retriveActivityRating(@PathVariable("ActivityRateId")Integer acr){
        return iActivityRatingService.retrieveActivityRating(acr);
    }
    @PostMapping("/addActivityRating")
    public ActivityRating addActivityRating(@RequestBody ActivityRating activityRatingc ){
        ActivityRating  activityRating= iActivityRatingService.addActivityRating(activityRatingc);
        return activityRatingc ;
    }
    @PutMapping("/updateActivityRating")
    public ActivityRating updateActivityRating(@RequestBody ActivityRating activityRating){
        ActivityRating acr= iActivityRatingService.updateActivityRating(activityRating);
        return acr;
    }
    @DeleteMapping("/delateActivityRating/{ActivityRating}")
    public void  delateActivityRating (@PathVariable ("ActivityRating") Integer activityRateId) {
        iActivityRatingService.deleteActivityRating(activityRateId);
    }
}
