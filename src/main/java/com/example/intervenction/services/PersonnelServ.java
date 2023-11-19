package com.example.intervenction.services;

import com.example.intervenction.entities.Etudiant;
import com.example.intervenction.entities.Personnel;

import java.util.List;

public interface PersonnelServ {
    List<Personnel> getAll();

    Personnel getOne(Long personnel_id);

    String add(Long departement_id,Personnel personnel);

    String updte(Long personnel_id, Long departement_id, Personnel personnel);

    String on(Long id);

    String off(Long id);

    List<Personnel> getPersonnelByDepartementId(Object id);
}
