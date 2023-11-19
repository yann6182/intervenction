package com.example.intervenction.implement;

import com.example.intervenction.entities.Utilisateur;
import com.example.intervenction.repositories.CategorieRepo;
import com.example.intervenction.repositories.DepartementRepo;
import com.example.intervenction.repositories.SouscategorieRepo;
import com.example.intervenction.repositories.UtilisateurRepo;
import com.example.intervenction.services.UtilisateurServ;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurImp implements UtilisateurServ {

    UtilisateurRepo utilisateurRepo;

    public UtilisateurImp( UtilisateurRepo utilisateurRepo ){
        this.utilisateurRepo = utilisateurRepo;
    }


    @Override
    public Utilisateur findByIdPassword(Long Id, String password) {
        return null;
    }
}
