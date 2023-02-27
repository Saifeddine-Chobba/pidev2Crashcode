package tn.esprit.pidevcrashcode.Services;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidevcrashcode.Entities.Activity;
import tn.esprit.pidevcrashcode.Repositories.ActivityRepository;

import java.util.List;

@Service
@Slf4j
public class ActivityService implements IActivityService {
    @Autowired
    ActivityRepository activityRepository;
    @Override
    public List<Activity> retrieveAllActivity() {
        return activityRepository.findAll();
    }

    @Override
    public Activity addActivity(Activity cc) {
        return  activityRepository.save(cc);
    }

    @Override
    public void deleteActivity(Integer id) {
        activityRepository.deleteById(id);

    }

    @Override
    public Activity updateActivity(Activity cc) {
        return activityRepository.save(cc);
    }

    @Override
    public Activity retrieveActivity(Integer id) {
        return activityRepository.findById(id).get();
    }
}
