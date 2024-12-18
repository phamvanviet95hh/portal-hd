package com.example.thanh_toan_asm.comparator;

import com.example.thanh_toan_asm.dtos.partnerToClue.ClueCustomDtos;
import com.example.thanh_toan_asm.entitys.Clue;

import java.util.List;
import java.util.Objects;

public class ServiceComparator {

    // Kiểm tra xem ProductA có trong danh sách ProductB dựa trên id không
    public boolean isInList(Clue productA, List<ClueCustomDtos> listB) {
        for (ClueCustomDtos b : listB) {
            if (Objects.equals(b.getId(), productA.getId())) {
                return true;
            }
        }
        return false;
    }

}
