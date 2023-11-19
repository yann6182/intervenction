package com.example.intervenction.services;

import com.example.intervenction.entities.Etudiant;
import com.example.intervenction.entities.Mail;

import java.util.List;

public interface MailServ {
    List<Mail> getAll();

    Mail getOne(Long id);

    String add(Long demande_id, Mail mail);

    void send(String etudiant, String message, String objet);

}
