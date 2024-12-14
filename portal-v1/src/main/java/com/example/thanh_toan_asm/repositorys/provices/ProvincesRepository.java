package com.example.thanh_toan_asm.repositorys.provices;

import com.example.thanh_toan_asm.entitys.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvincesRepository extends JpaRepository<Province, Long> {

}
