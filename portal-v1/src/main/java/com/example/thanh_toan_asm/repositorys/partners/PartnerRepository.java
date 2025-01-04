package com.example.thanh_toan_asm.repositorys.partners;

import com.example.thanh_toan_asm.dtos.partners.ResponsePartnerDtos;
import com.example.thanh_toan_asm.entitys.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {

    @Query("select p from Partner p where p.nameCompany like %:name%")
    List<Partner> customGetPartHome(String name);

}
