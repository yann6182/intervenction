package com.example.intervenction.services;

import com.example.intervenction.entities.Demande;
import com.example.intervenction.entities.Departement;

import java.util.List;

public interface DepartementServ {
    List<Departement> getAll();

    Departement getOne(Long id);

    String add(Departement departement);

    List<Demande> getDemandesByDepartement(Long departementId);

    String update(Long id, Departement departement);

    void delete(Long id); // Ajout de la méthode pour la suppression
}
