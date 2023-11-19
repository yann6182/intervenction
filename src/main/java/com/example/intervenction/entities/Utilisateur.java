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
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String email;
    private String telephone;
    private String login;
    private String password;
    private String statut;
    private String role;
    private String nomUtilisateur;
    private String motDePasse;
    private Long etudiantId;


    public Demande getPersonnel() {
        return null;
    }
}
