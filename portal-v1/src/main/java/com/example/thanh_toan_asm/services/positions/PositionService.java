package com.example.thanh_toan_asm.services.positions;

import com.example.thanh_toan_asm.entitys.Position;
import com.example.thanh_toan_asm.repositorys.positions.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    public List<Position> getListPosition() {
        return positionRepository.findAll();
    }
}
