package bouzri.me.springmvc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Medcin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 40)
    private String Nom;
    @Column(length = 40)
    private String Email;
    @Column(length = 40)
    private String specialite;

    @OneToMany(mappedBy = "medcin")
    private Collection<RendezVous> rendezVous;


}




