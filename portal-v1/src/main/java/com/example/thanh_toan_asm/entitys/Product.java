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

    @Column(columnDefinition = "TEXT")
    private String productImage;

    private String status;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "user_id", unique = false)
    private UserUntity userEntity;

    @ManyToOne
    @JoinColumn(name = "cate_id", unique = false)
    private CategoryProduct categoryProduct;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Contract> histories;


    public ConvertProductDto getVo() {
        ConvertProductDto convertProductDto = new ConvertProductDto();
        BeanUtils.copyProperties(this, convertProductDto);
        convertProductDto.setUserName(this.getUserEntity().getUserName());
        return convertProductDto;
    }

}
