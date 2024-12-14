package com.example.thanh_toan_asm.webControllers.admin.districts;


import com.example.thanh_toan_asm.dtos.BaseResponseList;
import com.example.thanh_toan_asm.dtos.provices.ResponseProvince;
import com.example.thanh_toan_asm.services.districts.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("web/district/")
public class DistrictWebController {

    @Autowired
    private DistrictService districtService;

    @GetMapping("getDistrict")
    public ResponseEntity<BaseResponseList<ResponseProvince>> getDistrict(@RequestParam("provincesId") String provincesId){
        return districtService.getDistrict(provincesId);
    }

}
