package com.example.thanh_toan_asm.services.provinces;

import com.example.thanh_toan_asm.dtos.BaseResponseList;
import com.example.thanh_toan_asm.dtos.provices.ResponseProvince;
import com.example.thanh_toan_asm.entitys.Province;
import com.example.thanh_toan_asm.repositorys.provices.ProvincesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProvinceService {

    @Autowired
    private ProvincesRepository provincesRepository;

    Boolean success;
    String message;

    public ResponseEntity<BaseResponseList<ResponseProvince>> getListProvince() {
        success = true;
        message = "Get data Success";
        List<Province> responseProvince = provincesRepository.findAll();
        List<ResponseProvince> responseProvinces = new ArrayList<>();
        for (Province item : responseProvince) {
            responseProvinces.add(item.getVo());
        }

        return new ResponseEntity<>(new BaseResponseList<>(success, message, responseProvinces),
                HttpStatusCode.valueOf(HttpStatus.OK.value()));

    }


    public List<ResponseProvince> getListProvinceWeb() {
        List<Province> responseProvince = provincesRepository.findAll();
        List<ResponseProvince> responseProvinces = new ArrayList<>();
        for (Province item : responseProvince) {
            responseProvinces.add(item.getVo());
        }
        return responseProvinces;
    }
}
