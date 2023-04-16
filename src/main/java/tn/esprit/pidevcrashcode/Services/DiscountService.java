package tn.esprit.pidevcrashcode.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidevcrashcode.Entities.Reservation;
import tn.esprit.pidevcrashcode.Entities.User;
import tn.esprit.pidevcrashcode.Repositories.ReservationRepository;
import tn.esprit.pidevcrashcode.Repositories.UserRepository;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DiscountService {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UserRepository userRepository;

    //Criteria

    private final int MIN_LENGHT_OF_STAY = 3;
    private final int MIN_NUMBER_OF_GUESTS = 4;
    private final double LOYALTY_DISCOUNT = 0.1;

    public float calculateDiscount(Reservation reservation){
        float discount = 0;

        //Apply discount based on lenght of stay
        long lenghtOfStay = ChronoUnit.DAYS.between(reservation.getDateFrom().toInstant(),reservation.getDateTo().toInstant());
        if(lenghtOfStay>= MIN_LENGHT_OF_STAY){
            discount +=0.05*lenghtOfStay;
        }

        //Apply discount based on number of guests
        if(reservation.getReservedSpots()>= MIN_NUMBER_OF_GUESTS){
            discount+=0.1;
        }

        //Apply discount for loyalty program members
        if(reservation.getUser().isLoyal()){
            discount +=0.25;
        }

        return discount;
    }

    public boolean isLoyal(int idUser ) {
        User user = userRepository.findById(idUser).get();
        int numberOfReservations = reservationRepository.findReservationByUser(idUser);
        if(numberOfReservations > 10){
            user.setLoyal(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
