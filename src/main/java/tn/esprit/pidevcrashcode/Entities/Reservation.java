package tn.esprit.pidevcrashcode.Entities;

import com.google.zxing.WriterException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.pidevcrashcode.Services.QrCodeGenerator;

import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReservation;
    private String username; //client who made reservation
    private String nameCampingCenter; //we use name in case we delete campingcenter
    private int campingCenterId;
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @Temporal(TemporalType.DATE)
    private Date dateFrom;
    @Temporal(TemporalType.DATE)
    private Date dateTo;
    private int reservedSpots;
    private float totalPrice; //reservedSpots*price (price tjibou mel camping center baad remise mtaa attribut discounhtPercent)
    @ManyToOne(fetch = FetchType.LAZY)
    private CampingCenter campingCenter;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private float discount;

    @Lob
    private byte[] QrCode;

    @Enumerated(EnumType.STRING)
    private Status status;
    // fama 4 status : ongoing=maaneha en cours wela canceled wela resolved=user mchee lel campingf center w jaou behi
    //waiting list : user yaadi reservation w ykhales w yekhtar ayamet jomaa li ynjm yji fihom mathalan sebt w ahad w awel matofrej blasa tetbaatrhlou notification
    // notification aal mail/tel w fil barre navigation(baad fil front) fiha date li ynjm yji feha
    // kif yaaml user cancel reservatrion status ykoun waiting ywali cancelled






}

