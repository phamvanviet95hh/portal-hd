package com.example.thanh_toan_asm.repositorys.positions;

import com.example.thanh_toan_asm.entitys.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
}
