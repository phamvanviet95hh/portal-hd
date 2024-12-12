package com.example.thanh_toan_asm.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.example.thanh_toan_asm.dtos.admins.products.ConvertProductDto;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productName;
    private String productDescription;
    private Long productPrice;
    private Long productOldPrice;
    private Long productSalePrice;

    @Column(columnDefinition = "TEXT")
    private String productImage;

    private String status;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;
    private String link;

    @ManyToOne
    @JoinColumn(name = "user_id", unique = false)
    private UserUntity userEntity;


    public ConvertProductDto getVo() {
        ConvertProductDto convertProductDto = new ConvertProductDto();
        BeanUtils.copyProperties(this, convertProductDto);
        convertProductDto.setUserName(this.getUserEntity().getUserName());
        return convertProductDto;
    }

}
