package com.example.intervenction.implement;

import com.example.intervenction.entities.Categorie;
import com.example.intervenction.entities.Demande;
import com.example.intervenction.entities.Departement;
import com.example.intervenction.entities.Mail;
import com.example.intervenction.repositories.DemandeRepo;
import com.example.intervenction.repositories.MailRepo;
import com.example.intervenction.services.MailServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MailImp implements MailServ {
    MailRepo mailRepo;
    DemandeRepo demandeRepo;


    @Autowired
    private JavaMailSender mailSender;


    public MailImp(MailRepo mailRepo, DemandeRepo demandeRepo){
        this.mailRepo = mailRepo;
        this.demandeRepo = demandeRepo;
    }


    @Override
    public List<Mail> getAll() {
        List<Mail> liste = new ArrayList<>();
        mailRepo.findAll().forEach(liste::add);
        return liste;
    }


    @Override
    public Mail getOne(Long id) {
        return mailRepo.findById(id).get();
    }

    @Override
    public String add(Long demande_id, Mail mail) {
        Optional<Demande> demandeOptional = demandeRepo.findById(demande_id);
        if (demandeOptional.isPresent()) {
            Demande demande = demandeOptional.get();
            mail.setDemande(demande);
            Mail save = mailRepo.save(mail);
            if (save != null) {
                this.send(demande.getEtudiant().getEmail(), mail.getMessage(), mail.getObjet());
                return "Le mail a été envoyé avec succès";
            }else{
                return "Le mail n'a pas pue etre envoyé";
            }
        }
        else {
            throw new IllegalArgumentException("Le département est introuvable, ID : " + demande_id);
        }
    }


    @Override
    public void send(String etudiant, String messages, String objet) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("gestioninterventiongroupe11@gmail.com");
        message.setTo(etudiant);
        message.setText(messages);
        message.setSubject(objet);
        mailSender.send(message);
    }
}
