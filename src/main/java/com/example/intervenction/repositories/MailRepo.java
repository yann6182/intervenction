package com.example.intervenction.repositories;

import com.example.intervenction.entities.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailRepo extends JpaRepository<Mail, Long> {
}
