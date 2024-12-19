package com.example.thanh_toan_asm.repositorys.clues;

import com.example.thanh_toan_asm.dtos.partnerToClue.ClueCustomDtos;
import com.example.thanh_toan_asm.entitys.Clue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ClueRepository extends JpaRepository<Clue, Long> {

    @Query("select c from Clue c where c.id = :idClue")
    Clue getOneClue(Long idClue);

    Clue findByEmailClue(String emailClue);

    @Query("select new com.example.thanh_toan_asm.dtos.partnerToClue.ClueCustomDtos(" +
            " c.id, c.nameClue, c.phoneClue, c.emailClue, c.positions.positionName as positionClue) from Clue c where c.id = :idClue")
    ClueCustomDtos getOneClueCustom(Long idClue);
}
