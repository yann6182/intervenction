package com.example.intervenction.implement;

import com.example.intervenction.entities.Categorie;
import com.example.intervenction.entities.Departement;
import com.example.intervenction.entities.Personnel;
import com.example.intervenction.entities.SousCategorie;
import com.example.intervenction.repositories.CategorieRepo;
import com.example.intervenction.repositories.DepartementRepo;
import com.example.intervenction.repositories.SouscategorieRepo;
import com.example.intervenction.services.SouscategorieServ;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SouscategorieImp implements SouscategorieServ {
    CategorieRepo categorieRepo;
    DepartementRepo departementRepo;
    private final SouscategorieRepo souscategorieRepo;

    public SouscategorieImp(
            SouscategorieRepo souscategorieRepo,
            DepartementRepo departementRepo,
            CategorieRepo categorieRepo
            ){
        this.souscategorieRepo = souscategorieRepo;
        this.departementRepo = departementRepo;
        this.categorieRepo = categorieRepo;
    }

    @Override
    public List<SousCategorie> getAll() {
        List<SousCategorie> liste = new ArrayList<>();
        souscategorieRepo.findAll().forEach(liste::add);
        return liste;
    }

    @Override
    public List<SousCategorie> getAllSousCategories() {
        List<SousCategorie> sousCategories = new ArrayList<>();
        souscategorieRepo.findAll().forEach(sousCategories::add);
        return sousCategories;
    }

    @Override
    public SousCategorie getSousCategorieById(Long sousCategorieId) {
        try {
            return souscategorieRepo.findById(sousCategorieId)
                    .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SousCategorie getOne(Long sousCategorie_id) {
        return souscategorieRepo.findById(sousCategorie_id).get();
    }

    @Override
    public String add(Long categorie_id, Long departement_id, SousCategorie sousCategorie) {
        Optional<Categorie> optionalCategorie = categorieRepo.findById(categorie_id);
        if (optionalCategorie.isPresent()) {
            Categorie categorie = optionalCategorie.get();

            Optional<Departement> optionalDepartement = departementRepo.findById(departement_id);
            if (optionalDepartement.isPresent()) {
                Departement departement = optionalDepartement.get();
                sousCategorie.setCategorie(categorie);
                sousCategorie.setDepartement(departement);

                SousCategorie save = souscategorieRepo.save(sousCategorie);
                if (save != null) {
                    return "La sous catégorie <b> " + save.getNom() + " </b> à bien été modifiée";
                }else {
                    return "La sous catégorie n'a pas été modifiée";
                }

            }else{
                throw new IllegalArgumentException("Le département est introuvable, ID : " + departement_id);
            }
        }else{
            throw new IllegalArgumentException("La catégorie est introuvable, ID : " + categorie_id);
        }
    }

    @Override
    public String update(Long sousCategorie_id, Long categorie_id, Long departement_id, SousCategorie sousCategorie) {
        Optional<Categorie> optionalCategorie = categorieRepo.findById(categorie_id);
        if (optionalCategorie.isPresent()) {
            Categorie categorie = optionalCategorie.get();

            Optional<Departement> optionalDepartement = departementRepo.findById(departement_id);
            if (optionalDepartement.isPresent()) {
                Departement departement = optionalDepartement.get();

                Optional<SousCategorie> optionalSousCategorie = souscategorieRepo.findById(departement_id);
                if (optionalSousCategorie.isPresent()) {
                    SousCategorie sousCategorie1 = optionalSousCategorie.get();
                    sousCategorie1.setDepartement(departement);
                    sousCategorie1.setCategorie(categorie);
                    sousCategorie1.setNom(sousCategorie.getNom());

                    SousCategorie update = souscategorieRepo.save(sousCategorie1);
                    if (update != null) {
                        return "La sous catégorie <b> " + update.getNom() + " </b> à bien été modifiée";
                    }else {
                        return "La sous catégorie n'a pas été modifiée";
                    }

                }else{
                    throw new IllegalArgumentException("La sous catégorie est introuvable, ID : " + sousCategorie_id);
                }
            }else{
                throw new IllegalArgumentException("Le département est introuvable, ID : " + departement_id);
            }
        }else{
            throw new IllegalArgumentException("La catégorie est introuvable, ID : " + categorie_id);
        }
    }

}
