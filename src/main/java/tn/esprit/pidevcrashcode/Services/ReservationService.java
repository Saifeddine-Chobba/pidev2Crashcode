package tn.esprit.pidevcrashcode.Services;

import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import tn.esprit.pidevcrashcode.Entities.CampingCenter;
import tn.esprit.pidevcrashcode.Entities.Reservation;
import tn.esprit.pidevcrashcode.Entities.Status;
import tn.esprit.pidevcrashcode.Entities.User;
import tn.esprit.pidevcrashcode.Repositories.CampingCenterRepository;
import tn.esprit.pidevcrashcode.Repositories.ReservationRepository;
import tn.esprit.pidevcrashcode.Repositories.UserRepository;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class ReservationService implements I_ReservationService{

    @Autowired
    ReservationRepository ResRepo;
    @Autowired
    CampingCenterRepository CcRepo;

    @Autowired
    CampingCenterService campingCenterService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    QrCodeGenerator qrCodeGenerator;
    @Autowired
    DiscountService discountService;

    public List<Reservation> findAllReservation(){
        return ResRepo.findAll();
    }
    public ResponseEntity<?>  addReservation(Reservation reservation, int idCc, int idUser)throws WriterException, IOException {



            //Get the user and the campsite to book for
            CampingCenter campingCenter=CcRepo.findById(idCc).get();
            User user = userRepository.findById(idUser).get();

            //Converting system date from LocalDateTime to Date
            LocalDateTime sysDateTime = LocalDateTime.now();
            Date sysDate = Date.from(sysDateTime.atZone(ZoneId.systemDefault()).toInstant());

            //Getting the available spots at a given date
            int remainingSpots = this.checkAvailableSpots(reservation.getDateFrom(),reservation.getDateTo(),idCc);

            //Getting the reservation count of a user at a given dates
            int userReservationNum = ResRepo.findByUserIdAndDateRange(idUser,reservation.getDateFrom(),reservation.getDateTo());

            //checking if there's an available spots(ONGOING) or no (WAITING)
            if ((remainingSpots>reservation.getReservedSpots()) )
            {
                        //setting the facture format, I have chosen txt.
                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.TEXT_PLAIN);
                        headers.setContentDisposition(ContentDisposition.builder("attachement").filename("facture.txt").build());


                if(userReservationNum ==0) { //checking if user have other reservations at the same date

                    //     setting the parameters

                    reservation.setStatus(Status.Ongoing);
                    reservation.setCreatedAt(sysDate);
                    reservation.setCampingCenter(campingCenter);
                    reservation.setCampingCenterId(idCc);
                    reservation.setUser(user);
                    reservation.setUsername(user.getUsername());

                    ResRepo.save(reservation);

                    //Generating a QrCode for reservation
                    String qrCodeText = "reservationId=" + reservation.getIdReservation();
                    byte[] QrCode = QrCodeGenerator.generateQrCodeImage(qrCodeText);
                    reservation.setQrCode(QrCode);
                    //Set the discount
                    float discount = discountService.calculateDiscount(reservation);
                    float totalPrice = this.calculateTotalPrice(reservation.getIdReservation());
                    reservation.setDiscount(discount);
                    reservation.setTotalPrice(totalPrice - discount);
                    ResRepo.save(reservation);

                    //Updating Available Spots at the campsite
                    campingCenter.setAvailableSpots(this.AvailableSpotsCensor(reservation, idCc));
                    CcRepo.save(campingCenter);

                    //Generating a facture
                    String facture = this.generatingFacture(reservation);


                    return new ResponseEntity<>(facture, headers, HttpStatus.CREATED);
                }
                else
                {
                    String errorMessage = "You have a busy calendar, you already had reservations at this date !";
                    return new ResponseEntity<>(errorMessage,headers,HttpStatus.CREATED);
                }
            }
            else //if there's no spots available
            {

            reservation.setStatus(Status.Waiting);
            reservation.setCampingCenterId(idCc);
            reservation.setCampingCenter(campingCenter);
            reservation.setUser(user);
            reservation.setUsername(user.getUsername());
            ResRepo.save(reservation);
            System.out.println("Sorry, this camping center is full,we have only "+remainingSpots+" spots available," +"your reservation is registred in waiting list, we will notify you when spots becomes available ! Sorry Again");
                return new ResponseEntity<>(reservation, HttpStatus.CREATED);
            }

    }



public Reservation CancelReservation(int idReservation){
       Reservation reservation= ResRepo.findById(idReservation).get();
        reservation.setStatus(Status.Cancelled);
      int totalSpots =  reservation.getCampingCenter().getAvailableSpots();
      int newtotalSpots = totalSpots+reservation.getReservedSpots();
         reservation.getCampingCenter().setAvailableSpots(newtotalSpots);
         CcRepo.save(reservation.getCampingCenter());
        return reservation;
}


    public String deleteReservation(int idReservation){
        ResRepo.deleteById(idReservation);
        return "Reservation deleted !";
    }

    public String resolvedStatus(int idReservation){

        LocalDateTime sysDateTime = LocalDateTime.now();
        Date sysDate = Date.from(sysDateTime.atZone(ZoneId.systemDefault()).toInstant());

        Reservation reservation= ResRepo.findById(idReservation).get();
        if(reservation==null){
            return "There's no reservation with this id";
        }
        else {
            int comparisonResult = reservation.getDateFrom().compareTo(sysDate);
            if(comparisonResult<=0 && this.checkQrCode(idReservation))//dateFrom > systemDate and Qr code
            {
                reservation.setStatus(Status.Resolved);
                //Updating Available spots in the campsite
                int totalSpots =  reservation.getCampingCenter().getAvailableSpots();
                int newtotalSpots = totalSpots+reservation.getReservedSpots();
                reservation.getCampingCenter().setAvailableSpots(newtotalSpots);
                CcRepo.save(reservation.getCampingCenter());
                return "You're welcome";
            }

        }
        return "Reservation not found";

    }

    public Boolean checkQrCode(int id){
       Reservation reservation = ResRepo.findById(id).get();
       if(reservation==null){
           return false;
       }
       else
       {
           return true;
       }
    }


    public Integer AvailableSpotsCensor(Reservation res, int idCc){
        CampingCenter cc = CcRepo.findById(idCc).get();
        int AvailableSpots = cc.getCapacity()-res.getReservedSpots();
        System.out.println(AvailableSpots);
        return AvailableSpots;
    }

    public  Integer  checkAvailableSpots(Date dateFrom,Date dateTo,int idCc){
        //Get the list of the overlapping reservations
        List<Reservation> overlappingReservations = ResRepo.findByCampingCenterIdAndDateRange(idCc,dateTo,dateFrom);

        //Get the total number of available spots in a camping center
        CampingCenter cc = campingCenterService.findCenterById(idCc);
        System.out.println(cc);
        int totalSpots = cc.getCapacity();
            System.out.println("totalSpots :" + totalSpots);
        boolean availableSpot ;
        //Calculate the number of reserved spots for the overlapping reservations
       int reservedSpotsForOverlappingRes = overlappingReservations.stream().mapToInt(Reservation::getReservedSpots).sum();
        System.out.println("reservedSpotsForOverlappingRes :" + reservedSpotsForOverlappingRes);
        //Calculate the remaining spots

       int remainingSpots = totalSpots- reservedSpotsForOverlappingRes;
       System.out.println("remaining spots :" + remainingSpots);
       //return overlappingReservations;
      //  return reservedSpotsForOverlappingRes;
        return remainingSpots;
    }

    public float calculateTotalPrice(int idReservation){
        Reservation reservation = ResRepo.findById(idReservation).get();
        long lenghtOfStay = ChronoUnit.DAYS.between(reservation.getDateFrom().toInstant(),reservation.getDateTo().toInstant());
         float totalPrice = (reservation.getReservedSpots())* (reservation.getCampingCenter().getPrice()) * lenghtOfStay;
         return totalPrice;
    }

    public String generatingFacture(Reservation reservation){
        //Generate the facture as a String
        String facture = "Facture\n\n"+
                "Client name :"+ reservation.getUsername()+"\n"+
                "From "+ reservation.getDateFrom()+"\n"+
                "To "+reservation.getDateTo()+"\n"+
                "Campsite name :" + reservation.getCampingCenter().getName()+"\n"+
                "Reserved spots :"+reservation.getReservedSpots()+"\n"+
                "Discount :"+reservation.getDiscount()+"\n"+
                "Final Price :" + reservation.getTotalPrice()+"TND"+"\n"+
                "Reservation created at :" + reservation.getCreatedAt()+"\n";
        return facture;

    }

    public void sendReminderEmails(){
        Date currentDate = new Date();
        Date startDate = new Date(currentDate.getTime());
       Date endDate = new Date(currentDate.getTime()+ TimeUnit.DAYS.toMillis(3));
        List<Reservation> reservations = ResRepo.findReservationsComingUpSoon(currentDate,endDate);
        for (Reservation reservation : reservations){
            //send email
        }
    }


}
