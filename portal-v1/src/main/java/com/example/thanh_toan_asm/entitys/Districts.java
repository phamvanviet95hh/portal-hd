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
@Table(name = "districts")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Districts {

    @Id
    private String code;

    private String name;
    private String nameEn;
    private String fullName;
    private String fullNameEn;
    private String codeName;

    @ManyToOne
    @JoinColumn(name = "province_code", nullable = true)
    private Province provinces;

    @ManyToOne
    @JoinColumn(name = "administrative_unit_id", nullable = true)
    private AdministrativeUnits administrativeUnits;

    @OneToMany(mappedBy = "districts", cascade = CascadeType.ALL)
    private Set<Ward> wards;

    @OneToMany(mappedBy = "districts", cascade = CascadeType.ALL)
    private Set<Partner> partners;

    @OneToMany(mappedBy = "districts", cascade = CascadeType.ALL)
    private Set<UserUntity> userUntities;

    public Districts(String districtId) {

        this.code = districtId;

    }

    public ResponseProvince getVo() {

        ResponseProvince responseDistrict = new ResponseProvince();
        BeanUtils.copyProperties(this, responseDistrict);
        return responseDistrict;

    }

}
