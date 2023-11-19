package com.example.intervenction.services;

import com.example.intervenction.entities.Categorie;
import com.example.intervenction.entities.Demande;
import com.example.intervenction.entities.Departement;
import com.example.intervenction.entities.SousCategorie;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DemandeServ {
    List<Demande> getAll();

    Demande getOne(Long id);

    String send(Long etudiant_id, long categorie_id, long departement_id, Long sous_categorie_id,String description, Demande demande);

    String prendreEnCharge(Long personnelId, Long demandeId, String commentaire);
    String prise_en_charge(Long personnel_id, Long id);

    String approuver(Long personnel_id, Long id);

    String rejetter(Long personnel_id, Long id);
    List<Departement> getAllDepartements();
    List<Categorie> getAllCategories();
    List<SousCategorie> getAllSousCategories();

    Departement getDepartementById(Long departementId);
    List<Demande> getDemandesByEtudiantId(Long etudiant);
    String add(Demande demande);

    List<Demande> getDemandesByDepartement(Long departementId);
}