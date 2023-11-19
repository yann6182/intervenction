package com.example.intervenction.services;

import com.example.intervenction.entities.Departement;
import com.example.intervenction.entities.Etudiant;

import java.util.List;

public interface EtudiantServ {
    List<Etudiant> getAll();

    Etudiant getOne(Long id);

    String add(Etudiant etudiant);

    String updte(Long id, Etudiant etudiant);

    String on(Long id);

    String off(Long id);

}
