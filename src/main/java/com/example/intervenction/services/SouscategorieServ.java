package com.example.intervenction.services;

import com.example.intervenction.entities.Categorie;
import com.example.intervenction.entities.SousCategorie;

import java.util.List;

public interface SouscategorieServ {
    List<SousCategorie> getAll();

    SousCategorie getOne(Long sousCategorie_id);

    String add(Long categorie_id, Long departement_id, SousCategorie sousCategorie);

    String update(Long sousCategorie_id, Long categorie_id, Long departement_id, SousCategorie sousCategorie);

    List<SousCategorie> getAllSousCategories();

    SousCategorie getSousCategorieById(Long sousCategorieId);
}
