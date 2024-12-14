package com.example.thanh_toan_asm.repositorys.districts;

import com.example.thanh_toan_asm.entitys.Districts;
import com.example.thanh_toan_asm.entitys.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<Districts, String> {
    List<Districts> findByProvinces(Province provinces);
}
