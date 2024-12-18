package com.example.thanh_toan_asm.services.clues;

import com.example.thanh_toan_asm.entitys.Clue;
import com.example.thanh_toan_asm.repositorys.clues.ClueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClueService {

    @Autowired
    private ClueRepository clueRepository;

    public List<Clue> getAllClue() {
        return clueRepository.findAll();
    }

    public Clue getOneClue(Long idClue) {
        return clueRepository.getOneClue(idClue);
    }
}
