package com.example.thanh_toan_asm.controllers;

import com.example.thanh_toan_asm.dtos.BaseResponse;
import com.example.thanh_toan_asm.dtos.admins.users.RequestCheckPassword;
import com.example.thanh_toan_asm.dtos.admins.users.ResponsePartner;
import com.example.thanh_toan_asm.dtos.registerUser.UserRegisterDto;
import com.example.thanh_toan_asm.dtos.registerUser.UserRegisterRespon;
import com.example.thanh_toan_asm.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RestController
@RequestMapping(value = "user/v1/")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping(value = "/add")
    public ResponseEntity<UserRegisterRespon> register(@RequestBody UserRegisterDto userRegisterDto, HttpServletRequest request){
        return userService.registerUser(userRegisterDto);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping(value = "checkPassword")
    public ResponseEntity<BaseResponse<RequestCheckPassword>> checkPassword(@RequestBody RequestCheckPassword checkPassword, HttpServletRequest request){
        return userService.checkPassword(checkPassword);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("updatePartner")
    public ResponseEntity<BaseResponse<ResponsePartner>> updatePartner(@RequestParam("data") String data,
                                                                          @RequestParam("file") MultipartFile file) throws JsonProcessingException {
        System.out.println(data);
        System.out.println(file);
        return  userService.updatePartner(data, file);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("deletePartner")
    public ResponseEntity<BaseResponse<ResponsePartner>> deletePartner(@RequestParam("id") String data) throws JsonProcessingException {
        Long idPart = Objects.equals(data, "") ? null : Long.parseLong(data);
        return  userService.deletePartner(idPart);
    }

}
