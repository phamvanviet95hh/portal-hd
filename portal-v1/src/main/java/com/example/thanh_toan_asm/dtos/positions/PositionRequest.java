package com.example.thanh_toan_asm.dtos.positions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PositionRequest {

    private String nameCompany;
    private String nameNDD;
    private String position;
    private String gender;
    private String phone;
    private String email;
    private String mst;
    private String stk;
    private String nameBank;
    private String idTinh;
    private String idHuyen;
    private String idXa;

}
