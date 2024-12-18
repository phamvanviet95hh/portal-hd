package com.example.thanh_toan_asm.repositorys.clues;

import com.example.thanh_toan_asm.dtos.BaseResponse;
import com.example.thanh_toan_asm.dtos.partnerToClue.ClueCustomDtos;
import com.example.thanh_toan_asm.dtos.provices.ResponseProvince;
import com.example.thanh_toan_asm.entitys.PartnerToClue;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnerToClueRepository extends JpaRepository<PartnerToClue, Long> {

    @Query("select new com.example.thanh_toan_asm.dtos.partnerToClue.ClueCustomDtos(ptc.clue.id, ptc.clue.nameClue, ptc.clue.phoneClue, ptc.clue.emailClue, ptc.clue.positions.positionName as positionClue) from PartnerToClue ptc where ptc.partner.id = :id")
    List<ClueCustomDtos> findByPartner(Long id);

    @Modifying
    @Transactional
    @Query("delete from PartnerToClue ptc where ptc.clue.id = :id and ptc.partner.id = :idPart")
    void deleteByClue(String id, Long idPart);

    @Query("select ptc from PartnerToClue ptc where ptc.clue.id = :id and ptc.partner.id = :idPart")
    PartnerToClue customGetClueAndPartner(Long id, Long idPart);
}
