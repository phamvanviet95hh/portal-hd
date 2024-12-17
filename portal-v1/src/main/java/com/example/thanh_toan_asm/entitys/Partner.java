package com.example.thanh_toan_asm.entitys;

import com.example.thanh_toan_asm.dtos.partners.CustomPartnerDtos;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "partners")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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


    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position positions;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;

    @ManyToOne
    @JoinColumn(name = "ward_id")
    private Ward ward;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private Districts districts;


    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL)
    private Set<Contract> histories;

    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL)
    private Set<PartnerToClue> partnerToClues;

    public CustomPartnerDtos getVo() {
        CustomPartnerDtos customPartnerDtos = new CustomPartnerDtos();
        BeanUtils.copyProperties(this, customPartnerDtos);
        customPartnerDtos.setAddress(this.province.getFullName() + " - " + this.districts.getFullName() + " - " + this.ward.getFullName());
        return customPartnerDtos;
    }
}
