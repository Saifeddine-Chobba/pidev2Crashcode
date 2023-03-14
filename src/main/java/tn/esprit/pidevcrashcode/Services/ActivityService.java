package tn.esprit.pidevcrashcode.Services;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidevcrashcode.Entities.*;
import tn.esprit.pidevcrashcode.Repositories.ActivityRepository;
import tn.esprit.pidevcrashcode.Repositories.CampCenterRepository;
import tn.esprit.pidevcrashcode.Repositories.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.*;

@Service
@Slf4j
@AllArgsConstructor
public class ActivityService implements IActivityService {
    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    CampCenterRepository campCenterRepository;
    @Autowired
    UserRepository userRepository;
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

//    @Override
//    public void addActivityAndAssignToCamCenter(Activity activity, int idCampCenter) {
//        CampingCenter campingCenter=campCenterRepository.findById(idCampCenter).get();
//        if(activity.getCampingCenters()==null){
//            List<CampingCenter> L =new ArrayList<>();
//            L.add(campingCenter);
//            activity.setCampingCenters(L);
//        }
//        else{
//        List<CampingCenter> L=activity.getCampingCenters();
//        L.add(campingCenter);
//        activity.setCampingCenters(L);
//        }
//        activityRepository.save(activity);
//    }

    @Override
    public float AverageRating(int id) {
        Activity activity=activityRepository.findById(id).get();
        float Average=0;
        Set<ActivityRating> ratings=activity.getActivityRatings();
        for (ActivityRating activityRating:ratings){
            Average=Average+activityRating.getRatingValue();
        }
        return Average/(ratings.size())  ;
    }

    @Override
    public void AddTofavorites(int idActivity, int idUser) {
        Activity activity=activityRepository.findById(idActivity).get();
        User user=userRepository.findById(idUser).get();
        Set<Activity> activities = user.getFavoriteActivities();
        activities.add(activity);
        user.setFavoriteActivities(activities);
        userRepository.save(user);
    }

    @Override
    public void delateFavoritActivities(int idActivity, int idUser) {
        Activity activity=activityRepository.findById(idActivity).get();
        User user=userRepository.findById(idUser).get();
        Set<Activity> activities = user.getFavoriteActivities();
        activities.remove(activity);
        user.setFavoriteActivities(activities);
        userRepository.save(user);
    }

    @Override
    public List<Activity> suggestActivities( int idCampCenter) {
        CampingCenter campingCenter=campCenterRepository.findById(idCampCenter).get();
        List<Activity> activities=null;
        for (Activity a:activityRepository.findAll()){
            if (a.getTypeActivity().toString().equals(campingCenter.getCategory().toString())){
                activities.add(a);
            }
        }

        return activities;
    }
    @Override
    public Set<Activity> suggestActivitiesByPreference(Set<Activity> preferences ){
        if (preferences.isEmpty()){
            return new HashSet<>();
        }
        if (preferences.size() == 4){
            return preferences;
        }
        if (preferences.size() > 4){
            while(preferences.size() > 4){
                preferences.remove(0);
            }
            return preferences;
        }

        Set<Activity> toBeChecked = new HashSet<>();
        //find all activities to check
        for (Activity activity : preferences){
            toBeChecked.addAll(getActivitiesOfType(activity.getTypeActivity()));
        }

        while(toBeChecked.size() > 4 ){
            toBeChecked.remove(0);
        }
        return toBeChecked;
    }
    @Override
    public List<Activity> getActivitiesOfType(TypeActivity typeActivity){
        return  activityRepository.findAllByTypeActivity(typeActivity);
    }

    @Override
    public List getDataFromCSVFile() {
        return null;
    }

    @Override
    public  void main(String[] args) throws IOException {
        String fileName = "camping_data.csv";
        FileReader reader = new FileReader(fileName);
        CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
        List<CSVRecord> records = parser.getRecords();

        for (CSVRecord record : records) {
            String gender = record.get("Gender");
            int age = Integer.parseInt(record.get("Age"));
            String state = record.get("State");
            int kayaking = Integer.parseInt(record.get("Kayaking"));
            int canoeing = Integer.parseInt(record.get("Canoeing"));
            int rafting = Integer.parseInt(record.get("Rafting"));
            int fishing = Integer.parseInt(record.get("Fishing"));
            int snorkeling = Integer.parseInt(record.get("Snorkeling"));
            int scubaDiving = Integer.parseInt(record.get("Scuba diving"));
            int swimming = Integer.parseInt(record.get("Swimming"));
            int waterSkiing = Integer.parseInt(record.get("Water skiing"));
            int wakeboarding = Integer.parseInt(record.get("Wakeboarding"));
            int rockClimbing = Integer.parseInt(record.get("Rock climbing"));
            int bouldering = Integer.parseInt(record.get("Bouldering"));
            int trailRunning = Integer.parseInt(record.get("Trail running"));
            int mountainBiking = Integer.parseInt(record.get("Mountain biking"));
            int camelTrekking = Integer.parseInt(record.get("Camel trekking"));
            int sandboarding = Integer.parseInt(record.get("Sandboarding"));
            int duneBashing = Integer.parseInt(record.get("Dune bashing"));
            int hotAirBalloonRiding = Integer.parseInt(record.get("Hot air balloon riding"));
            int atvTours = Integer.parseInt(record.get("ATV tours"));
            int birdWatching = Integer.parseInt(record.get("Bird watching"));
            int mushroomHunting = Integer.parseInt(record.get("Mushroom hunting"));
            int treeClimbing = Integer.parseInt(record.get("Tree climbing"));
            int zipLining = Integer.parseInt(record.get("Zip lining"));

            // Do something with the data, such as storing it in a database or analyzing it.
        }

        parser.close();
    }
}



