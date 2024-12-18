package com.example.thanh_toan_asm.repositorys.clues;

import com.example.thanh_toan_asm.entitys.Clue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ClueRepository extends JpaRepository<Clue, Long> {

    @Query("select c from Clue c where c.id = :idClue")
    Clue getOneClue(Long idClue);
}
