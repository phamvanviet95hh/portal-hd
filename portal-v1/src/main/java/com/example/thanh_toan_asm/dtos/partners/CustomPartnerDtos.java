package com.example.thanh_toan_asm.dtos.partners;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CustomPartnerDtos {
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
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String address;
}
