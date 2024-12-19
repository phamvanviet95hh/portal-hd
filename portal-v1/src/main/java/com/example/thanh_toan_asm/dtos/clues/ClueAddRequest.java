package com.example.thanh_toan_asm.dtos.clues;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClueAddRequest {

    private String clueName;
    private String phoneClue;
    private String emailClue;
    private Long positionClue;

}
