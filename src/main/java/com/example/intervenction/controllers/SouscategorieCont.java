package com.example.intervenction.controllers;

import com.example.intervenction.entities.SousCategorie;
import com.example.intervenction.services.SouscategorieServ;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class SouscategorieCont {
    SouscategorieServ souscategorieServ;

    public SouscategorieCont(SouscategorieServ souscategorieServ) {
        this.souscategorieServ = souscategorieServ;
    }

    @GetMapping("/allsCat")
    public List<SousCategorie> allsCat(){
        List<SousCategorie> categorieList = souscategorieServ.getAll();
        return categorieList;
    }

    @GetMapping("/onesCat/{id}")
    public SousCategorie onesCat(@PathVariable(value = "id") Long id){
        SousCategorie categorie = souscategorieServ.getOne(id);
        return categorie;
    }

    @PostMapping("/addsCat/{categorie_id}/{departement_id}")
    public String addsCat(@PathVariable(value = "categorie_id") Long categorie_id, @PathVariable(value = "departement_id") Long departement_id, @RequestBody SousCategorie sousCategorie){
        return souscategorieServ.add(categorie_id, departement_id, sousCategorie);
    }

    @PutMapping("/updatesCat/{souscategorie_id}/{categorie_id}/{departement_id}")
    public String updatesCat(@PathVariable(value = "souscategorie_id") Long souscategorie_id, @PathVariable(value = "categorie_id") Long categorie_id, @PathVariable(value = "departement_id") Long departement_id,  @RequestBody SousCategorie sousCategorie){
        return souscategorieServ.update(souscategorie_id, categorie_id, departement_id, sousCategorie);
    }
}
