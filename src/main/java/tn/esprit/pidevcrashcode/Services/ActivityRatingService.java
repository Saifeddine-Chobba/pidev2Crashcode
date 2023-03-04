package tn.esprit.pidevcrashcode.Services;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidevcrashcode.Entities.Activity;
import tn.esprit.pidevcrashcode.Entities.ActivityRating;
import tn.esprit.pidevcrashcode.Repositories.ActivityRatingRepository;
import tn.esprit.pidevcrashcode.Repositories.ActivityRepository;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ActivityRatingService implements IActivityRatingService  {
    @Autowired
    ActivityRatingRepository activityRatingRepository;

    @Autowired
    ActivityRepository activityRepository;




    @Override
    public List<ActivityRating> retrieveAllActivityRating() {
        return activityRatingRepository.findAll() ;
    }

    @Override
    public ActivityRating addActivityRating(ActivityRating activityRating) {
        return  activityRatingRepository.save(activityRating);
    }

    @Override
    public void deleteActivityRating(Integer id) {
        activityRatingRepository.deleteById(id);
    }

    @Override
    public ActivityRating updateActivityRating(ActivityRating cc) {
        return activityRatingRepository.save(cc);
    }

    @Override
    public ActivityRating retrieveActivityRating(Integer id) {
        return activityRatingRepository.findById(id).get();
    }

    @Override
    public void addActivityAndAssignToActivityRating(ActivityRating activityRating, int idActivity) {
        Activity activity = activityRepository.findById(idActivity).get();
        activityRating.setActivity(activity);
        activityRatingRepository.save(activityRating);
    }
}
