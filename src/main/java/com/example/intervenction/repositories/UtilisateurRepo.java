package com.example.intervenction.repositories;

import com.example.intervenction.entities.Etudiant;
import com.example.intervenction.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepo extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByIdAndMotDePasse(Long id, String motDePasse);

    Optional<Utilisateur> findById(Long id);


}