package tn.esprit.pidevcrashcode.Services;

import org.springframework.http.ResponseEntity;
import tn.esprit.pidevcrashcode.Entities.Review;

import java.util.List;

public interface I_ReviewService {

    public List<Review> findAllReviews();
    public ResponseEntity<?> addReview(Review r, int idUser, int campsiteId);
    public String deleteReview(Review r);
}
