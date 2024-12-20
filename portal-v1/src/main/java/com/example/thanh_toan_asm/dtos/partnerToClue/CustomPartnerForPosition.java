package com.example.thanh_toan_asm.dtos.partnerToClue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomPartnerForPosition {

    private Long id;
    private String fullName;
    private String nameCompany;
    private String phone;
    private String email;
    private String mst;
    private String stk;
    private String bank;
    private String gender;
    private String position;

}
