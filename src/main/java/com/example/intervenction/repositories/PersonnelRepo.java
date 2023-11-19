package com.example.intervenction.repositories;

import com.example.intervenction.entities.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonnelRepo extends JpaRepository<Personnel, Long> {
}
