package tn.esprit.pidevcrashcode.Controllers;

import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidevcrashcode.Entities.CampingCenter;
import tn.esprit.pidevcrashcode.Entities.Reservation;
import tn.esprit.pidevcrashcode.Entities.Status;
import tn.esprit.pidevcrashcode.Repositories.ReservationRepository;
import tn.esprit.pidevcrashcode.Services.ReservationService;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
public class ReservationController {

    @Autowired
    ReservationService reservationService;
    @Autowired
    ReservationRepository rr;

    @PostMapping("/client/reservation/add")
    public ResponseEntity<?> addReservation(@RequestBody Reservation reservation,@RequestParam int idCc,@RequestParam int idUser)throws WriterException, IOException {

            System.out.println(reservation.getReservedSpots());


        return reservationService.addReservation(reservation,idCc,idUser);
    }

    @GetMapping("/client/reservations")
    public List<Reservation> findAllReservation(){
        return reservationService.findAllReservation();
    }
    @GetMapping("/client/treservations")
    public List<Reservation> findAllReservations(@RequestParam int idCc, @RequestParam("dateFrom") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom, @RequestParam("dateTo") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo){
       System.out.println(reservationService.checkAvailableSpots(dateFrom,dateTo,idCc));
        return rr.findByCampingCenterIdAndDateRange(idCc,dateFrom,dateTo);
    }
    @DeleteMapping("/client/reservation/{id}")
    public String DeleteReservation(@PathVariable("id") int id){
         rr.deleteById(id);
       return "deleted succesfully";
    }

    @PutMapping("/client/reservation/{id}")
    public Reservation cancelReservation(@PathVariable("id") int idReservation){

        return reservationService.CancelReservation(idReservation);
    }

    @GetMapping("/qr/{id}")
    public ResponseEntity<byte[]> getReservationQrcode(@PathVariable("id")int id){
        Reservation reservation = rr.findById(id).get();
        byte[] qrCodeImage = reservation.getQrCode();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);


        return new ResponseEntity<>(qrCodeImage,headers,HttpStatus.OK);
    }

    @PutMapping("/reservation/{idRes}")
    public String checkResolvedReservations(@PathVariable("idRes") int idRes ){
        return  reservationService.resolvedStatus(idRes);
    }
/*
    @GetMapping("/reservationss")
    public List<Reservation> lough(){
        return reservationService.getReservationComingUpSoon(3);
    }
    */


}
