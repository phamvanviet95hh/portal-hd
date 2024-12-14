package com.example.thanh_toan_asm.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "category_product")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoryProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productCategoryName;
    private String productCategoryDescription;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "categoryProduct", cascade = CascadeType.ALL)
    private Set<Contract> histories;

    @OneToMany(mappedBy = "categoryProduct", cascade = CascadeType.ALL)
    private Set<Product> products;

    @ManyToOne
    @JoinColumn(name = "user_id", unique = false)
    private UserUntity userEntity;
}
