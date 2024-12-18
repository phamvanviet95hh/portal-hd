package com.example.thanh_toan_asm.repositorys.provices;

import com.example.thanh_toan_asm.entitys.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProvincesRepository extends JpaRepository<Province, Long> {

    Province findByCode(String provinceId);

    Province getByCode(String code);
}
