package com.example.thanh_toan_asm.services;

import com.example.thanh_toan_asm.confignations.SystemBe;
import com.example.thanh_toan_asm.dtos.BaseResponse;
import com.example.thanh_toan_asm.dtos.admins.users.CustomListUser;
import com.example.thanh_toan_asm.dtos.admins.users.RequestCheckPassword;
import com.example.thanh_toan_asm.dtos.admins.users.RequestUpdatePartnerDtos;
import com.example.thanh_toan_asm.dtos.admins.users.ResponsePartner;
import com.example.thanh_toan_asm.dtos.registerUser.UserRegisterDto;
import com.example.thanh_toan_asm.dtos.registerUser.UserRegisterRespon;
import com.example.thanh_toan_asm.entitys.*;
import com.example.thanh_toan_asm.repositorys.UserRepository;
import com.example.thanh_toan_asm.repositorys.districts.DistrictRepository;
import com.example.thanh_toan_asm.repositorys.partners.PartnerRepository;
import com.example.thanh_toan_asm.repositorys.provices.ProvincesRepository;
import com.example.thanh_toan_asm.repositorys.wards.WardsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class UserService {
    private Boolean success;
    private String message;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProvincesRepository provinceRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private WardsRepository wardsRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    int statusCode = 0;
    public ResponseEntity<UserRegisterRespon> registerUser(UserRegisterDto userRegisterDto){

                UserUntity register = userRepository.findFirstByUserName(userRegisterDto.getUserName());
                if (register == null) {
                    Province province = provinceRepository.findByCode(userRegisterDto.getProvinceId());
                    Districts districts = districtRepository.findByCode(userRegisterDto.getDistrictId());
                    Ward ward = wardsRepository.findByCode(userRegisterDto.getWardId());
                    success = true;
                    message = "Insert data success!!!";
                    register = UserUntity.builder()
                            .updateAt(LocalDateTime.now())
                            .avatar(userRegisterDto.getAvatar())
                            .userName(userRegisterDto.getUserName())
                            .createAt(LocalDateTime.now())
                            .province(province)
                            .districts(districts)
                            .ward(ward)
                            .password(passwordEncoder.encode(userRegisterDto.getPassword()))
                            .role(userRegisterDto.getRole())
                            .status(userRegisterDto.getStatus())
                            .fullName(userRegisterDto.getFullName())
                            .email(userRegisterDto.getEmail())
                            .phone(userRegisterDto.getPhone())
                            .build();
                    log.info("ward :" + ward.toString());
                    log.info("districts :" + districts.toString());
                    log.info("province :" + province.toString());
                    log.info("register :" + register.toString());
                    UserUntity userEntity = userRepository.save(register);
                    System.out.println(userEntity.getId());
                    statusCode = HttpStatus.CREATED.value();
                    success = true;
                    message = "Register Success !!!";
                }else {
                    success = false;
                    message = "Insert data Fail, User already exists!!!";
                    statusCode = HttpStatus.BAD_REQUEST.value();
                }

        return new ResponseEntity<>(UserRegisterRespon.builder()
                .success(success)
                .message(message)
                .build(), HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }


    public UserUntity getInfoUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public ResponseEntity<BaseResponse<RequestCheckPassword>> checkPassword(RequestCheckPassword checkPassword) {
        try {
            UserUntity checkPartner = userRepository.findById(checkPassword.getUserId()).get();
            if (passwordEncoder.matches(checkPassword.getPassword(), checkPartner.getPassword())) {
                checkPartner.setPassword(passwordEncoder.encode(checkPassword.getNewPassword()));
                userRepository.save(checkPartner);
                success = true;
                message = "Cập nhập mật khẩu mới thành công!!!";
            } else {
                success = false;
                message = "Vui lòng kiểm tra lại mật khẩu cũ!!!";
            }
        }catch (Exception e){
            success = false;
            message = "Không tìm thấy tài khoản cần thay đổi.";
            e.printStackTrace();
        }

        return new ResponseEntity<>(new BaseResponse<>(
                success, message, null
        ), HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    public UserUntity findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public ResponseEntity<BaseResponse<ResponsePartner>> updatePartner(String data, MultipartFile file)
            throws JsonProcessingException {

        ResponsePartner responsePartner = null;
        ObjectMapper objectMapper = new ObjectMapper();
        RequestUpdatePartnerDtos requestProductCategoryDto = objectMapper.readValue(data,
                RequestUpdatePartnerDtos.class);
        if (file.isEmpty()) {
            success = false;
            message = "Không có file";
            return new ResponseEntity<>(new BaseResponse<>(
                    success, message, null), HttpStatusCode.valueOf(HttpStatus.OK.value()));
        }
        try {
            byte[] bytes = file.getBytes();
            String base64Image = Base64.getEncoder().encodeToString(bytes);
            UserUntity partner = userRepository.findById(requestProductCategoryDto.getId()).get();

            BeanUtils.copyProperties(requestProductCategoryDto, partner,
                    SystemBe.getNullPropertyNames(requestProductCategoryDto));
            partner.setUpdateAt(LocalDateTime.now());
            if (base64Image != null) {
                partner.setAvatar(base64Image);
            }
            UserUntity userUntity =  userRepository.save(partner);
            responsePartner = ResponsePartner.builder()
                    .avatar(userUntity.getAvatar())
                    .phone(userUntity.getPhone())
                    .email(userUntity.getEmail())
                    .fullName(userUntity.getFullName())
                    .build();
            success = true;
            message = "Updated Successfully";
        } catch (Exception e) {
            success = false;
            message = "Updated False";
            e.printStackTrace();
        }

        return new ResponseEntity<>(new BaseResponse<>(
                success, message, responsePartner), HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    public List<CustomListUser> customGetListUser() {
        return userRepository.customGetListUser();
    }

    public ResponseEntity<BaseResponse<ResponsePartner>> deletePartner(Long idPart) {
        try {
            UserUntity userUntity = userRepository.findById(idPart)
                    .orElseThrow(() -> new RuntimeException("No user in table"));
            userRepository.delete(userUntity);
            success = true;
            message = "Delete Success!!!";
        }catch (Exception e){
            success = false;
            message = "Delete Fail!!!";
        }

        return new ResponseEntity<>(new BaseResponse<>(
                success, message, null), HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }


    public List<UserUntity> loadListUserFrom(String data) {
        return userRepository.loadListUserFrom(data);
    }
}
