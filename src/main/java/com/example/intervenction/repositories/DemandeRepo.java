package com.example.intervenction.repositories;

import com.example.intervenction.entities.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeRepo extends JpaRepository<Demande, Long> {
    List<Demande> findByEtudiantId(Long etudiant);

   // List<Demande> findByDepartementId(Long departementId);
}
