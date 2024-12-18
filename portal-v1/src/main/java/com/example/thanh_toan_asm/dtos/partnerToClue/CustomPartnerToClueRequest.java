package com.example.thanh_toan_asm.dtos.partnerToClue;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomPartnerToClueRequest {
    private ArrayList<Long> data;
    private Long idPart;
}
