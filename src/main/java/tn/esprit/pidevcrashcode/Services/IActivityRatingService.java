package tn.esprit.pidevcrashcode.Services;

import tn.esprit.pidevcrashcode.Entities.Activity;
import tn.esprit.pidevcrashcode.Entities.ActivityRating;

import java.util.List;

public interface IActivityRatingService {
    List<ActivityRating> retrieveAllActivityRating();

    ActivityRating addActivityRating(ActivityRating activityRating);

    void deleteActivityRating(Integer id);

    ActivityRating updateActivityRating(ActivityRating cc);

    ActivityRating retrieveActivityRating(Integer id);

    void addActivityAndAssignToActivityRating(ActivityRating activity, int Activity);
}
