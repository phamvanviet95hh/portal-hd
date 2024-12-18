package com.example.thanh_toan_asm.dtos.partnerToClue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClueCustomDtos {

    private Long id;
    private String nameClue;
    private String phoneClue;
    private String emailClue;
    private String positionClue;

}
