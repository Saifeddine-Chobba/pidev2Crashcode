package tn.esprit.pidevcrashcode.Services;


import tn.esprit.pidevcrashcode.Entities.Activity;

import java.util.List;

public interface IActivityService {
    List<Activity> retrieveAllActivity();

    Activity addActivity(Activity cc);

    void deleteActivity(Integer id);

    Activity updateActivity(Activity cc);

    Activity retrieveActivity(Integer id);
}
