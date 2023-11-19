package com.example.intervenction.implement;

import com.example.intervenction.entities.Departement;
import com.example.intervenction.entities.Etudiant;
import com.example.intervenction.entities.Personnel;
import com.example.intervenction.repositories.DepartementRepo;
import com.example.intervenction.repositories.EtudiantRepo;
import com.example.intervenction.repositories.PersonnelRepo;
import com.example.intervenction.services.PersonnelServ;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonnelImp implements PersonnelServ {

    PersonnelRepo personnelRepo;
    DepartementRepo departementRepo;

    public PersonnelImp(
            PersonnelRepo personnelRepo,
            DepartementRepo departementRepo){
        this.personnelRepo = personnelRepo;
        this.departementRepo = departementRepo;
    }


    @Override
    public List<Personnel> getAll() {
        List<Personnel> liste = new ArrayList<>();
        personnelRepo.findAll().forEach(liste::add);
        return liste;
    }

    @Override
    public Personnel getOne(Long personnel_id) {
        return personnelRepo.findById(personnel_id).get();
    }

    @Override
    public String add(Long departement_id, Personnel personnel) {
        Optional<Departement> departementOptional = departementRepo.findById(departement_id);
        if (departementOptional == null) {
            Departement departement = departementOptional.get();
            personnel.setDepartement(departement);
            Personnel save = personnelRepo.save(personnel);
            if (save != null) {
                return "Le personnel <b> " + personnel.getNom().toUpperCase() + " </b> à été modifié";
            }else {
                return "Le département <b> " +  personnel.getNom().toUpperCase() + " </b> n'a pas été modifié";
            }
        }else {
            throw new IllegalArgumentException("Le personnel est introuvable, ID : " + departement_id);
        }
    }

    @Override
    public String updte(Long personnel_id, Long departement_id, Personnel personnel) {
        Optional<Departement> departementOptional = departementRepo.findById(departement_id);
        if (departementOptional == null) {
            Departement departement = departementOptional.get();
            personnel.setDepartement(departement);
            Optional<Personnel> personnelOptional = personnelRepo.findById(departement_id);
            if (departementOptional == null) {
                Personnel personnel1 = personnelOptional.get();
                personnel1.setDepartement(departement);
                personnel1.setNom(personnel.getNom());
                personnel1.setLogin(personnel.getLogin());
                personnel1.setEmail(personnel.getEmail());
                personnel1.setPassword(personnel.getPassword());
                personnel1.setTelephone(personnel.getTelephone());
                personnel1.setPoste(personnel.getPoste());
                Personnel save = personnelRepo.save(personnel1);
                if (save != null) {
                    return "Le personnel <b> " + personnel.getNom().toUpperCase() + " </b> à été modifié";
                }else {
                    return "Le département <b> " +  personnel.getNom().toUpperCase() + " </b> n'a pas été modifié";
                }
            }else {
                throw new IllegalArgumentException("Le personnel est introuvable, ID : " + personnel_id);
            }
        }else {
            throw new IllegalArgumentException("Le département est introuvable, ID : " + departement_id);
        }
    }

    @Override
    public String on(Long id) {
        Optional<Personnel> personnelOptional = personnelRepo.findById(id);
        if (personnelOptional.isPresent()) {
            Personnel personnel = personnelOptional.get();
            personnel.setStatut("actif");
            Personnel save = personnelRepo.save(personnel);
            if (save != null) {
                return "Le compte du personnel <b> " + personnel.getNom().toUpperCase() + " </b> à été réactivé";
            }else {
                return "Le compte du personnel <b> " +  personnel.getNom().toUpperCase() + " </b> n'a pas pue etre réactivé";
            }
        }
        else {
            throw new IllegalArgumentException("Le compte du personnel est introuvable, ID : " + id);
        }
    }

    @Override
    public String off(Long id) {
        Optional<Personnel> personnelOptional = personnelRepo.findById(id);
        if (personnelOptional.isPresent()) {
            Personnel personnel = personnelOptional.get();
            personnel.setStatut("inactif");
            Personnel save = personnelRepo.save(personnel);
            if (save != null) {
                return "Le compte du personnel <b> " + personnel.getNom().toUpperCase() + " </b> à été désactivé";
            }else {
                return "Le compte du personnel <b> " +  personnel.getNom().toUpperCase() + " </b> n'a pas pue etre désactivé";
            }
        }
        else {
            throw new IllegalArgumentException("Le compte du personnel est introuvable, ID : " + id);
        }
    }

    @Override
    public List<Personnel> getPersonnelByDepartementId(Object id) {
        return null;
    }
}
