package tn.esprit.pidevcrashcode.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidevcrashcode.Entities.Reservation;
import tn.esprit.pidevcrashcode.Entities.User;
import tn.esprit.pidevcrashcode.Repositories.ReservationRepository;

import java.util.Date;
import java.util.List;

@Service
public class MatchService {

    @Autowired
    ReservationRepository reservationRepository;
/*
    public List<User> findMatchesForUser(int idUser, Date dateFrom, Date dateTo, int idCc){
        List<Reservation> overlappingReservations = reservationRepository.findByCampingCenterIdAndDateRange(idCc,dateTo,dateFrom);

    }*/
}
