package com.example.intervenction.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Data
@Table(name = "departements")
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;

    @OneToMany(mappedBy = "departement")
    private List<SousCategorie> sousCategorieList;

    //@OneToMany(mappedBy = "departement")
    //private List<Personnel> personnels;
}
