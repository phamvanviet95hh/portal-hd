package com.example.thanh_toan_asm.dtos.admins.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomListUser {

    private Long id;
    private String userName;
    private String fullName;
    private String role;
    private LocalDateTime createAt;
}
