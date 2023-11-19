package com.example.intervenction.implement;

import com.example.intervenction.entities.Etudiant;
import com.example.intervenction.entities.Utilisateur;
import com.example.intervenction.repositories.UtilisateurRepo;
import com.example.intervenction.services.AuthenticationService;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UtilisateurRepo utilisateurRepo;

    public AuthenticationServiceImpl(UtilisateurRepo utilisateurRepo) {
        this.utilisateurRepo = utilisateurRepo;
    }

    @Override
    public Utilisateur authentifier(Long id, String motDePasse) {
        Optional<Utilisateur> utilisateurOpt = utilisateurRepo.findByIdAndMotDePasse(id, motDePasse);
        return utilisateurOpt.orElse(null);
    }

    @Override
    public Long getEtudiantId(Long etudiant) {
        return null;
    }










   /* public Long getEtudiantId(String nomUtilisateur) {
        // Utilisez le repository ou tout autre mécanisme pour obtenir l'ID de l'étudiant
        Optional<Utilisateur> utilisateurOpt = utilisateurRepo.findByNomUtilisateur(nomUtilisateur);

        return utilisateurOpt.map(Utilisateur::getEtudiantId).orElse(null);
    }*/
}
