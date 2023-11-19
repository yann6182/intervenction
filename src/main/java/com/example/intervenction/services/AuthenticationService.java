package com.example.intervenction.services;

import com.example.intervenction.entities.Utilisateur;
import com.example.intervenction.repositories.UtilisateurRepo;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {

    Utilisateur authentifier(Long id, String motDePasse);

    Long getEtudiantId(Long etudiant);
}
