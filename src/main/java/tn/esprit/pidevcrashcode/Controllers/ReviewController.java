package tn.esprit.pidevcrashcode.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.pidevcrashcode.Entities.Review;
import tn.esprit.pidevcrashcode.Services.ReviewService;

@RestController
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping("/review/add") //matastithech
    public ResponseEntity<?> addReview(Review r, int idUser, int campsiteId){
       return reviewService.addReview(r,idUser,campsiteId);
    }
}
