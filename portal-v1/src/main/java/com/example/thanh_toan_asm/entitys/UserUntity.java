package com.example.thanh_toan_asm.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserUntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public UserUntity(Long id) {
        this.id = id;
    }

    @Column(unique = true)
    private String userName;

    private String fullName;
    private String password;
    private String email;
    private String phone;
    private String status;

    @Column(columnDefinition = "TEXT")
    private String avatar;
    private String role;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;


    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private Set<Product> productSet;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Contract> histories;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;

    @ManyToOne
    @JoinColumn(name = "ward_id")
    private Ward ward;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private Districts districts;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private Set<CategoryProduct> categoryProducts;

}
