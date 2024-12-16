package com.example.thanh_toan_asm.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomUserInforDto {

    private Long id;
    private String userName;
    private String fullName;
    private String password;
    private String email;
    private String phone;
    private String status;
    private String avatar;
    private String role;
    private LocalDateTime createAt;
    private String address;

}
