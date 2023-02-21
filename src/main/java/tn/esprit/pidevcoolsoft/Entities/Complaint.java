package tn.esprit.pidevcoolsoft.Entities;

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
public class Complaint implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComplaint;
    @Enumerated(EnumType.STRING)
    private TypeComplaint typeComplaint;
    private String username;
    @Temporal(TemporalType.DATE)
    private Date dateComplaint;
    private String description;
    @Column(nullable = true)
    private String imageName;
}
