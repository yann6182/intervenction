package com.example.intervenction.implement;

import com.example.intervenction.entities.*;
import com.example.intervenction.repositories.*;
import com.example.intervenction.services.DemandeServ;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemandeImp implements DemandeServ {

    private final DepartementRepo departementRepo;



    private final DemandeRepo demandeRepo;
    private final SouscategorieRepo souscategorieRepo;
    private final CategorieRepo categorieRepo;

    private final EtudiantRepo etudiantRepo;
    private final PersonnelRepo personnelRepo;

    public DemandeImp(
            DemandeRepo demandeRepo,
            SouscategorieRepo souscategorieRepo,
            PersonnelRepo personnelRepo,
            EtudiantRepo etudiantRepo,
            CategorieRepo categorieRepo,
            DepartementRepo departementRepo,
            DepartementRepo departementRepo1) {
        this.demandeRepo = demandeRepo;
        this.souscategorieRepo = souscategorieRepo;
        this.etudiantRepo = etudiantRepo;
        this.personnelRepo = personnelRepo;
        this.categorieRepo= categorieRepo;
        this.departementRepo = departementRepo;

    }

    @Override
    public List<Demande> getAll() {
        List<Demande> liste = new ArrayList<>();
        demandeRepo.findAll().forEach(liste::add);
        return liste;
    }

    @Override
    public Departement getDepartementById(Long departementId) {
        return departementRepo.findById(departementId)
                .orElseThrow(() -> new IllegalArgumentException("Département introuvable, ID : " + departementId));
    }



    @Override
    public String add(Demande demande) {
        return null;
    }

    @Override
    public List<Demande> getDemandesByDepartement(Long departementId) {
        return null;
    }

    @Override
    public Demande getOne(Long id) {
        return demandeRepo.findById(id).orElse(null);
    }

    @Override
    public String send(Long etudiant_id, long categorieRepo, long departementRepo, Long sous_categorie_id,String description, Demande demande) {
        SousCategorie sousCategorie = souscategorieRepo.findById(sous_categorie_id)
                .orElseThrow(() -> new IllegalArgumentException("La sous-catégorie est introuvable, ID : " + sous_categorie_id));

        Etudiant etudiant = etudiantRepo.findById(etudiant_id)
                .orElseThrow(() -> new IllegalArgumentException("L'étudiant est introuvable, ID : " + etudiant_id));

        demande.setEtudiant(etudiant);
        demande.setSousCategorie(sousCategorie);
        demande.setDescription(description);
        Demande save = demandeRepo.save(demande);

        return (save != null) ?
                "La demande de l'étudiant <b> " + etudiant.getNom().toUpperCase()
                        + " </b> pour la sous-catégorie <b>" + sousCategorie.getNom()
                        + " </b> a bien été envoyée" :
                "La demande n'a pas été envoyée";
    }


    @Override
    public String prendreEnCharge(Long personnelId, Long demandeId, String commentaire) {
        // Vérifiez si le personnel appartient au même département que la demande
        Personnel personnel = personnelRepo.findById(personnelId)
                .orElseThrow(() -> new IllegalArgumentException("Le personnel est introuvable, ID : " + personnelId));

        Demande demande = demandeRepo.findById(demandeId)
                .orElseThrow(() -> new IllegalArgumentException("La demande est introuvable, ID : " + demandeId));

        if (!personnel.getDepartement().equals(demande.getEtudiant().getDepartement())) {
            return "Le personnel ne peut pas prendre en charge cette demande car il n'appartient pas au même département.";
        }

        // Mettez à jour le statut de la demande et enregistrez le commentaire
        demande.setPersonnel(personnel);
        demande.setStatut("prise en charge");
        demande.setCommentaire(commentaire);

        Demande save = demandeRepo.save(demande);

        return (save != null) ?
                "La demande de l'étudiant <b>" + demande.getEtudiant().getNom() + "</b> a été prise en charge" :
                "La demande de l'étudiant <b>" + demande.getEtudiant().getNom() + "</b> n'a pas été prise en charge";
    }

    @Override
    public String prise_en_charge(Long personnel_id, Long id) {
        Personnel personnel = personnelRepo.findById(personnel_id)
                .orElseThrow(() -> new IllegalArgumentException("Le personnel est introuvable, ID : " + personnel_id));

        Demande demande = demandeRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La demande est introuvable, ID : " + id));

        demande.setPersonnel(personnel);
        demande.setStatut("prise en charge");

        Demande save = demandeRepo.save(demande);

        return (save != null) ?
                "La demande de l'étudiant <b> " + demande.getEtudiant().getNom() + " </b> a été prise en charge" :
                "La demande de l'étudiant <b> " + demande.getEtudiant().getNom() + " </b> n'a pas été prise en charge";
    }

    @Override
    public String approuver(Long personnel_id, Long id) {
        Personnel personnel = personnelRepo.findById(personnel_id)
                .orElseThrow(() -> new IllegalArgumentException("Le personnel est introuvable, ID : " + personnel_id));

        Demande demande = demandeRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La demande est introuvable, ID : " + id));

        demande.setPersonnel(personnel);
        demande.setStatut("finalisée");

        Demande save = demandeRepo.save(demande);

        return (save != null) ?
                "La demande de l'étudiant <b> " + demande.getEtudiant().getNom() + " </b> a été finalisée" :
                "La demande de l'étudiant <b> " + demande.getEtudiant().getNom() + " </b> n'a pas pu être finalisée";
    }
    @Override
    public List<Departement> getAllDepartements() {
        List<Departement> liste = new ArrayList<>();
        departementRepo.findAll().forEach(liste::add);
        return liste;
    }

    @Override
    public List<Categorie> getAllCategories() {
        List<Categorie> Categories = new ArrayList<>();
        categorieRepo.findAll().forEach(Categories::add);
        return Categories;
    }

    @Override
    public List<SousCategorie> getAllSousCategories() {
        List<SousCategorie> liste = new ArrayList<>();
        souscategorieRepo.findAll().forEach(liste::add);
        return liste;
    }

    @Override
    public List<Demande> getDemandesByEtudiantId(Long etudiant) {
        return demandeRepo.findByEtudiantId(etudiant);
    }

    @Override
    public String rejetter(Long personnel_id, Long id) {
        Personnel personnel = personnelRepo.findById(personnel_id)
                .orElseThrow(() -> new IllegalArgumentException("Le personnel est introuvable, ID : " + personnel_id));

        Demande demande = demandeRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La demande est introuvable, ID : " + id));

        demande.setPersonnel(personnel);
        demande.setStatut("rejetée");

        Demande save = demandeRepo.save(demande);

        return (save != null) ?
                "La demande de l'étudiant <b> " + demande.getEtudiant().getNom() + " </b> a été rejetée" :
                "La demande de l'étudiant <b> " + demande.getEtudiant().getNom() + " </b> n'a pas pu être rejetée";
    }
}
