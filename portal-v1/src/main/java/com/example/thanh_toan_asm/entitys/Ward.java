package com.example.thanh_toan_asm.entitys;

import com.example.thanh_toan_asm.dtos.provices.ResponseProvince;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Set;

@Entity
@Table(name = "wards")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class Ward {

    @Id
    private String code;

    private String name;
    private String nameEn;
    private String fullName;
    private String fullNameEn;
    private String codeName;

    @ManyToOne
    @JoinColumn(name = "district_code", nullable = true)
    private Districts districts;

    @ManyToOne
    @JoinColumn(name = "administrative_unit_id", nullable = true)
    private AdministrativeUnits administrativeUnits;

    @OneToMany(mappedBy = "ward", cascade = CascadeType.ALL)
    private Set<Partner> partners;

    @OneToMany(mappedBy = "ward", cascade = CascadeType.ALL)
    private Set<UserUntity> userUntities;

    public Ward(Long wardId) {
        this.code = wardId.toString();
    }


    public ResponseProvince getVo() {
        ResponseProvince responseProvince = new ResponseProvince();
        BeanUtils.copyProperties(this,responseProvince);
        return responseProvince;
    }
}
