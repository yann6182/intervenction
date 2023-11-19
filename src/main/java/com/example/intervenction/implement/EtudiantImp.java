package com.example.intervenction.implement;

import com.example.intervenction.entities.Departement;
import com.example.intervenction.entities.Etudiant;
import com.example.intervenction.repositories.DepartementRepo;
import com.example.intervenction.repositories.EtudiantRepo;
import com.example.intervenction.services.EtudiantServ;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EtudiantImp implements EtudiantServ {
    EtudiantRepo etudiantRepo;

    public EtudiantImp(EtudiantRepo etudiantRepo){
        this.etudiantRepo = etudiantRepo;
    }

    @Override
    public List<Etudiant> getAll() {
        List<Etudiant> liste = new ArrayList<>();
        etudiantRepo.findAll().forEach(liste::add);
        return liste;
    }



    @Override
    public Etudiant getOne(Long id) {
        return etudiantRepo.findById(id).get();
    }



    @Override
    public String add(Etudiant etudiant) {
        Etudiant save = etudiantRepo.save(etudiant);
        if (save != null) {
            return "L'étudiant <b>  </b> à bien été enregistré";
        }else {
            return "L'étudiant n'a pas été enregistré";
        }
    }



    @Override
    public String updte(Long id, Etudiant etudiant) {
        Optional<Etudiant> etudiantOptional = etudiantRepo.findById(id);
        if (etudiantOptional.isPresent()) {
            Etudiant etudiant1 = etudiantOptional.get();
            etudiant1.setNom(etudiant.getNom());
            etudiant1.setEmail(etudiant.getEmail());
            etudiant1.setTelephone(etudiant.getTelephone());
            etudiant1.setLogin(etudiant.getLogin());
            etudiant1.setPassword(etudiant.getPassword());
            etudiant1.setMatricule(etudiant.getMatricule());
            Etudiant save = etudiantRepo.save(etudiant1);
            if (save != null) {
                return "L'étudiant <b> " + etudiant.getNom().toUpperCase() + " </b> à été modifié";
            }else {
                return "L'étudiant <b> " +  etudiant.getNom().toUpperCase() + " </b> n'a pas été modifié";
            }
        }
        else {
            throw new IllegalArgumentException("Le département est introuvable, ID : " + id);
        }
    }



    @Override
    public String on(Long id) {
        Optional<Etudiant> etudiantOptional = etudiantRepo.findById(id);
        if (etudiantOptional.isPresent()) {
            Etudiant etudiant = etudiantOptional.get();
            etudiant.setStatut("actif");
            Etudiant save = etudiantRepo.save(etudiant);
            if (save != null) {
                return "Le compte de l'étudiant <b> " + etudiant.getNom().toUpperCase() + " </b> à été réactivé";
            }else {
                return "Le compte de l'étudiant <b> " +  etudiant.getNom().toUpperCase() + " </b> n'a pas pue etre réactivé";
            }
        }
        else {
            throw new IllegalArgumentException("Le compte de l'étudiant est introuvable, ID : " + id);
        }
    }



    @Override
    public String off(Long id) {
        Optional<Etudiant> etudiantOptional = etudiantRepo.findById(id);
        if (etudiantOptional.isPresent()) {
            Etudiant etudiant = etudiantOptional.get();
            etudiant.setStatut("inactif");
            Etudiant save = etudiantRepo.save(etudiant);
            if (save != null) {
                return "Le compte de l'étudiant <b> " + etudiant.getNom().toUpperCase() + " </b> à été désactivé";
            }else {
                return "Le compte de l'étudiant <b> " +  etudiant.getNom().toUpperCase() + " </b> n'a pas pue désactivé réactivé";
            }
        }
        else {
            throw new IllegalArgumentException("Le compte de l'étudiant est introuvable, ID : " + id);
        }
    }
}
