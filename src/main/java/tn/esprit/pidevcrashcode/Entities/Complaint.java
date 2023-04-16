package tn.esprit.pidevcrashcode.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Complaint implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComplaint;
    @Enumerated(EnumType.STRING)
    private TypeComplaint typeComplaint;
    private String username;  //estaaml getCurrentUser() bch tjib user li ada complaint mawjouda fi UserService
    @Temporal(TemporalType.DATE)
    private Date dateComplaint;
    private String description;
    @Column(nullable = true)
    private String imageName;  // esm image li yhabatha l user bil upload
}
