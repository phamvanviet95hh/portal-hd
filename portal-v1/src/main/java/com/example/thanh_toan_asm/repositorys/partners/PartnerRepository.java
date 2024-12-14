package com.example.thanh_toan_asm.repositorys.partners;

import com.example.thanh_toan_asm.entitys.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
}
