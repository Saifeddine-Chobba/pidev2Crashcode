package tn.esprit.pidevcrashcode.Repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tn.esprit.pidevcrashcode.Entities.Reservation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {





    @Query("Select r FROM Reservation r "+
    "Where r.campingCenterId = :campingCenterId "+
    "AND ((r.dateTo >= :dateFrom AND r.dateFrom <= :dateFrom) "+
    "OR (r.dateFrom>= :dateFrom And r.dateFrom <=:dateTo))" +
    "And r.status = 'ONGOING' ")
     List<Reservation> findByCampingCenterIdAndDateRange(@Param("campingCenterId") int campingCenterId,
                                                        @Param("dateFrom") Date dateFrom,
                                                        @Param("dateTo") Date dateTo);
/*
    @Query("Select r FROM Reservation r "+
            "Where user_id = :user_id "+
            "AND ((r.dateTo >= :dateFrom AND r.dateFrom <= :dateFrom) "+
            "OR (r.dateFrom>= :dateFrom And r.dateFrom <=:dateTo))" +
            "And r.status = 'ONGOING' ")
    List<Reservation> findByUserIdAndDateRange(@Param("user_id") int user_id,
                                                        @Param("dateFrom") Date dateFrom,
                                                        @Param("dateTo") Date dateTo);
    */
    @Query("Select count(r) from Reservation r "+
            "where r.user.idUser = :user_id "+
            "And((r.dateTo >= :dateFrom AND r.dateFrom <= :dateFrom) "+
            "OR (r.dateFrom>= :dateFrom And r.dateFrom <=:dateTo))" +
            "And r.status = 'ONGOING' ")
    Integer findByUserIdAndDateRange(@Param("user_id") int user_id,
                                              @Param("dateFrom") Date dateFrom,
                                              @Param("dateTo") Date dateTo);

    @Query("SELECT r from Reservation r "+
    "Where r.user.idUser = :idUser "+
    "And r.campingCenterId = :campingCenterId "+
            "And r.status = 'RESOLVED' ")
    List<Reservation> findReservationByUserAndCampsiteId(@Param("idUser") int idUser,
                                                         @Param("campingCenterId") int campingCenterId);


    @Query("Select r from Reservation r "+
            "Where r.status = 'ONGOING' "+
            "and (r.dateFrom between :startDate and :endDate) ")
    List<Reservation> findReservationsComingUpSoon(@Param("startDate") Date start,@Param("endDate") Date end);


    @Query("SELECT count(r) from Reservation r "+
            "Where r.user.idUser = :idUser "+
            "And r.status = 'RESOLVED' ")
    Integer findReservationByUser(@Param("idUser") int idUser);





}
