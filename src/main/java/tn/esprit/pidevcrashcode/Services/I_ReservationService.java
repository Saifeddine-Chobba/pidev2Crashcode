package tn.esprit.pidevcrashcode.Services;

import com.google.zxing.WriterException;
import org.springframework.http.ResponseEntity;
import tn.esprit.pidevcrashcode.Entities.Reservation;

import java.io.IOException;
import java.util.List;

public interface I_ReservationService {

    public List<Reservation> findAllReservation();

    public ResponseEntity<?> addReservation(Reservation reservation,int idCc, int iduser)throws WriterException, IOException;
    //public String deleteReservation(Reservation Res);
    public String deleteReservation(int idReservation);
}
