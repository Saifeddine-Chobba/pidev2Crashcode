package tn.esprit.pidevcrashcode.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.esprit.pidevcrashcode.Entities.*;
import tn.esprit.pidevcrashcode.Repositories.CampingCenterRepository;
import tn.esprit.pidevcrashcode.Repositories.RatingRepository;
import tn.esprit.pidevcrashcode.Repositories.UserRepository;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@Service
public class RatingService {
    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    CampingCenterRepository campingCenterRepository;

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<Rating> addRating(int ratingValue, int idUser, int campsiteId){
        Rating rating = new Rating();
        CampingCenter campingCenter = campingCenterRepository.findById(campsiteId).get();
        User user = userRepository.findById(idUser).get();
        rating.setCampingCenter(campingCenter);
        rating.setIdUser(user.getIdUser());
        rating.setRatingValue(ratingValue);
//rating.setCampsiteId(campsiteId);
        ratingRepository.save(rating);
     //   this.updateCampsiteRate(campsiteId);
        return new ResponseEntity<>(rating, HttpStatus.CREATED);
    }


    public void updateCampsiteRate(int campsiteId){
        List<Rating> ratingList = ratingRepository.findRatingsbycampsiteid(campsiteId);
        float campsiteRateSum = ratingList.stream().mapToInt(Rating::getRatingValue).sum();
        System.out.println("campsiteRateSum" +campsiteRateSum);
        float campsiteRate = campsiteRateSum/ratingList.size();
        System.out.println("campsiteRate" +campsiteRate);
       CampingCenter campingCenter= campingCenterRepository.findById(campsiteId).get();
        campingCenter.setRating(campsiteRate);
        campingCenterRepository.save(campingCenter);
    }
}
