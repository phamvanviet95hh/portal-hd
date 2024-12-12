package com.example.thanh_toan_asm.repositorys;

import com.example.thanh_toan_asm.dtos.admins.users.CustomListUser;
import com.example.thanh_toan_asm.entitys.UserUntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserUntity, Long> {
    UserUntity findFirstByUserName(String username);

    @Query("select new com.example.thanh_toan_asm.dtos.admins.users.CustomListUser(u.id, u.userName, u.fullName, u.role, u.createAt) from UserUntity u")
    List<CustomListUser> customGetListUser();
}
