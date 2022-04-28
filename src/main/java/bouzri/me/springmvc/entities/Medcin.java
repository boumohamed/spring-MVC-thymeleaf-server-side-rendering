package bouzri.me.springmvc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Medcin {
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty
    @Size(min = 4, max = 40)
    @Column(length = 40)
    private String nom;

    @NotEmpty
    @Size( min = 4, max = 40)
    @Column(length = 40, unique = true)
    private String Email;

    @NotEmpty
    @Size(min = 4, max = 40)
    @Column(length = 40)
    private String Speciality;



}
