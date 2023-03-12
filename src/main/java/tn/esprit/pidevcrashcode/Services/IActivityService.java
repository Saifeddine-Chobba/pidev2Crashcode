package tn.esprit.pidevcrashcode.Services;


import tn.esprit.pidevcrashcode.Entities.Activity;
import tn.esprit.pidevcrashcode.Entities.CampingCenter;
import tn.esprit.pidevcrashcode.Entities.Rating;
import tn.esprit.pidevcrashcode.Entities.TypeActivity;

import java.util.List;
import java.util.Set;

public interface IActivityService {
    List<Activity> retrieveAllActivity();

    Activity addActivity(Activity cc);

    void deleteActivity(Integer id);

    Activity updateActivity(Activity cc);

    Activity retrieveActivity(Integer id);

//     void addActivityAndAssignToCamCenter(Activity activity,int idCampCenter);
    float AverageRating (int activity);

    void  AddTofavorites (int idActivity,int idUser);

    void  delateFavoritActivities (int idActivity,int idUser);

    List<Activity> suggestActivities (int idCampCenter);

    public Set<Activity> suggestActivitiesByPreference(Set<Activity> preferences );

    public List<Activity> getActivitiesOfType(TypeActivity typeActivity);
}
