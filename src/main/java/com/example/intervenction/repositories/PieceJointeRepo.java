package com.example.intervenction.repositories;

import com.example.intervenction.entities.PieceJointe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PieceJointeRepo extends JpaRepository<PieceJointe, Long> {
}
