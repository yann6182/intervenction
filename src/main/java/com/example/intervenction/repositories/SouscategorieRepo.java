package com.example.intervenction.repositories;

import com.example.intervenction.entities.SousCategorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SouscategorieRepo extends JpaRepository<SousCategorie, Long> {
}
