package com.example.intervenction.implement;

import com.example.intervenction.entities.Categorie;
import com.example.intervenction.entities.SousCategorie;
import com.example.intervenction.repositories.CategorieRepo;
import com.example.intervenction.services.CategorieServ;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategorieImp implements CategorieServ {
    private final  CategorieRepo categorieRepo;

    public CategorieImp(CategorieRepo categorieRepo) {
        this.categorieRepo = categorieRepo;
    }

    @Override
    public List<Categorie> getAll() {
        List<Categorie> liste = new ArrayList<>();
        categorieRepo.findAll().forEach(liste::add);
        return liste;
    }

    @Override
    public List<Categorie> getAllCategories() {
        List<Categorie> Categories = new ArrayList<>();
        categorieRepo.findAll().forEach(Categories::add);
        return Categories;
    }

    @Override
    public Categorie getOne(Long id) {
        return categorieRepo.findById(id).get();
    }

    @Override
    public String add(Categorie categorie) {
        final Categorie save = categorieRepo.save(categorie);
        if (save != null) {
            return "La catégorie <b> " + save.getNom() + " </b> à bien été enregistrée";
        } else {
            return "La catégorie n'a pas été enregistrée";
        }
    }

    @Override
    public String update(Long id, Categorie categorie) {
        Optional<Categorie> categorieOptional = categorieRepo.findById(id);
        if (categorieOptional.isPresent()) {
            Categorie update = categorieOptional.get();
            update.setNom(categorie.getNom());
            Categorie save = categorieRepo.save(update);
            if (save != null) {
                return "La catégorie <b> " + save.getNom() + " </b> à bien été modifiée";
            } else {
                return "La catégorie n'a pas été modifiée";
            }
        } else {
            throw new IllegalArgumentException("La catégorie est introuvable, ID : " + id);
        }
    }

    @Override
    public void clean(Long id) {
        categorieRepo.deleteById(id);
    }

    @Override
    public Categorie getCategorieById(Long categorieId) {
        try {
            return categorieRepo.findById(categorieId)
                    .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
