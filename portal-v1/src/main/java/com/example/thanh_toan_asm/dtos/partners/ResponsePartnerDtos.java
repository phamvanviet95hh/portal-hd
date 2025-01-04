package com.example.thanh_toan_asm.dtos.partners;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponsePartnerDtos {

    private Long partnerId;
    private String nameCompany;
    private String nameNDD;
    private String position;
    private String gender;
    private String phone;
    private String email;
    private String mst;
    private String stk;
    private String nameBank;
    private String addressKh;
    private String addressBank;
}
