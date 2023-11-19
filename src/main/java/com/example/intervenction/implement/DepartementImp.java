package com.example.intervenction.implement;

import com.example.intervenction.entities.Demande;
import com.example.intervenction.entities.Departement;
import com.example.intervenction.repositories.DemandeRepo;
import com.example.intervenction.repositories.DepartementRepo;
import com.example.intervenction.services.DepartementServ;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class DepartementImp implements DepartementServ {
    DepartementRepo departementRepo;
    DemandeRepo demandeRepo;

    public DepartementImp(DepartementRepo departementRepo) {
        this.departementRepo = departementRepo;
    }

    @Override
    public List<Departement> getAll() {
        List<Departement> liste = new ArrayList<>();
        departementRepo.findAll().forEach(liste::add);
        return liste;
    }

    @Override
    public Departement getOne(Long id) {
        return departementRepo.findById(id).get();
    }

    @Override
    public String add(Departement departement) {
        Departement save = departementRepo.save(departement);
        if (save != null) {
            return "Le département <b> " + departement.getNom().toUpperCase() + " </b> a bien été enregistré";
        } else {
            return "Le département n'a pas été enregistré";
        }
    }
    @Override
    public List<Demande> getDemandesByDepartement(Long departementId) {
        // Implémentez la logique pour récupérer les demandes spécifiques à un département
        // Utilisez votre repository ou tout autre mécanisme pour accéder à la base de données
        // Remplacez ceci par la logique réelle
        return null; //demandeRepo.findByDepartementId(departementId);
    }


    @Override
    public String update(Long id, Departement departement) {
        Optional<Departement> departementOptional = departementRepo.findById(id);
        if (departementOptional.isPresent()) {
            Departement departement1 = departementOptional.get();
            departement1.setNom(departement.getNom());
            Departement save = departementRepo.save(departement1);
            if (save != null) {
                return "Le département <b> " + departement.getNom().toUpperCase() + " </b> a été modifié";
            } else {
                return "Le département <b> " +  departement.getNom().toUpperCase() + " </b> n'a pas été modifié";
            }
        } else {
            throw new IllegalArgumentException("Le département est introuvable, ID : " + id);
        }
    }
    @Override
    public void delete(Long id) {
        departementRepo.deleteById(id);
    }
}
