package com.example.thanh_toan_asm.controllers.partners;


import com.example.thanh_toan_asm.dtos.BaseResponse;
import com.example.thanh_toan_asm.dtos.partners.PartnerRequestDtos;
import com.example.thanh_toan_asm.dtos.positions.PositionRequest;
import com.example.thanh_toan_asm.dtos.provices.ResponseProvince;
import com.example.thanh_toan_asm.entitys.Districts;
import com.example.thanh_toan_asm.entitys.Partner;
import com.example.thanh_toan_asm.entitys.Position;
import com.example.thanh_toan_asm.services.clues.PartnerToClueService;
import com.example.thanh_toan_asm.services.districts.DistrictService;
import com.example.thanh_toan_asm.services.partners.PartnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "api/partner/")
@Slf4j
public class PartnerApiController {


    @Autowired
    private PartnerService partnerService;

    @Autowired
    private PartnerToClueService partnerToClueService;


    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "add")
    public ResponseEntity<BaseResponse<ResponseProvince>> add(@RequestBody PartnerRequestDtos partnerRequestDtos) {
        return partnerService.addPartner(partnerRequestDtos);
    }


}
