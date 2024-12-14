package com.example.thanh_toan_asm.services.wards;

import com.example.thanh_toan_asm.dtos.BaseResponseList;
import com.example.thanh_toan_asm.dtos.provices.ResponseProvince;
import com.example.thanh_toan_asm.entitys.Districts;
import com.example.thanh_toan_asm.entitys.Ward;
import com.example.thanh_toan_asm.repositorys.wards.WardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WardService {

    @Autowired
    private WardsRepository wardsRepository;


    Boolean success;
    String message;

    public ResponseEntity<BaseResponseList<ResponseProvince>> getListWard(String districtId) {

        success = true;
        message = "Get data Success";
        List<Ward> reWards = wardsRepository.findByDistricts(new Districts(districtId));
        List<ResponseProvince> responseWards = new ArrayList<>();
        for(Ward item : reWards){
            responseWards.add(item.getVo());
        }

        return  new ResponseEntity<>(new BaseResponseList<>(success, message, responseWards), HttpStatusCode.valueOf(HttpStatus.OK.value()));

    }
}
