package tn.esprit.pidevcrashcode.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.pidevcrashcode.Entities.Rating;
import tn.esprit.pidevcrashcode.Entities.Review;
import tn.esprit.pidevcrashcode.Services.RatingService;

@RestController
public class RatingController {

    @Autowired
    RatingService ratingService;

    @PostMapping("client/rating")

    public ResponseEntity<Rating> addRating(@RequestParam("ratingValue") int ratingValue, @RequestParam("idUser") int idUser,@RequestParam("campsiteId") int campsiteId){


        return ratingService.addRating(ratingValue,idUser,campsiteId);
    }
}
