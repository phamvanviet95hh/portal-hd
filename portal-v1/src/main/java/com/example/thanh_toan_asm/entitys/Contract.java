package com.example.thanh_toan_asm.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "contract")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String contractCode;
    private LocalDateTime timeTrienKhai;
    private String addressCsTrienKhai;
    private String nameCsTrienKhai;
    private String qtyProduct;
    private String dvtProduct;
    private String totalMoney;
    private String totalMoneyTruocThue;
    private String thue;
    private String totalMoneySauThue;
    private String totalMoneyBangChu;

    private String dauMoiB;
    private String chucVuDauMoiB;
    private String sdtDauMoiB;
    private String emailDauMoiB;
    private String status;
    private String mailNhanHd;


    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserUntity user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cate_id")
    private CategoryProduct categoryProduct;

    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position positions;

}
