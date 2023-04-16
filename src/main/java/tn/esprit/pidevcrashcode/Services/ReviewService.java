package tn.esprit.pidevcrashcode.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import tn.esprit.pidevcrashcode.Entities.CampingCenter;
import tn.esprit.pidevcrashcode.Entities.Review;
import tn.esprit.pidevcrashcode.Entities.User;
import tn.esprit.pidevcrashcode.Repositories.CampingCenterRepository;
import tn.esprit.pidevcrashcode.Repositories.ReservationRepository;
import tn.esprit.pidevcrashcode.Repositories.ReviewRepository;
import tn.esprit.pidevcrashcode.Repositories.UserRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class ReviewService implements I_ReviewService{

    @Autowired
    ReviewRepository ReviewRepo;
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CampingCenterRepository campingCenterRepository;

    public List<Review> findAllReviews(){
        return ReviewRepo.findAll();
    }
    public ResponseEntity<?> addReview(Review review, int idUser, int campsiteId){

        //Converting system date from LocalDateTime to Date
        LocalDateTime sysDateTime = LocalDateTime.now();
        Date sysDate = Date.from(sysDateTime.atZone(ZoneId.systemDefault()).toInstant());

        //checking if the user has visit the campsite already
        //if he didn't, he can't add a review
        if (reservationRepository.findReservationByUserAndCampsiteId(idUser,campsiteId).isEmpty())
        {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_PLAIN);


            String errorMessage = "You did not visited this campsite yet, you can't add a review !";
            return new ResponseEntity<>(errorMessage,headers, HttpStatus.CREATED);
        }
        else {
            CampingCenter campingCenter = campingCenterRepository.findById(campsiteId).get();
            User user = userRepository.findById(idUser).get();
            review.setUser(user);
            review.setCampingCenter(campingCenter);

            review.setReviewDate(sysDate);
            ReviewRepo.save(review);
            return new ResponseEntity<>(review,HttpStatus.CREATED);
        }

    }
    public String deleteReview(Review r){
        ReviewRepo.delete(r);
        return "Deleted";
    }

}
