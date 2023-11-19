package com.example.intervenction.services;

import com.example.intervenction.entities.PieceJointe;

import java.util.List;

public interface PieceJointeServ {
    List<PieceJointe> getAll();

    PieceJointe getOne(Long id);

    String add(Long demande_id, PieceJointe pieceJointe);

    String update(Long pieceJointe_id, Long demande_id, PieceJointe pieceJointe);

    String clean(Long pieceJointe_id);
}
