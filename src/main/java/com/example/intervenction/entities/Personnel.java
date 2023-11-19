package com.example.intervenction.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Data
@Table(name = "personnels")
public class Personnel extends Utilisateur {
    private String poste;
    private String nom;
    private String prenom;

    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;

    public Departement getDepartement() {
        return departement;
    }
}
