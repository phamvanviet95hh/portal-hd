package com.example.thanh_toan_asm.controllers.partners;


import com.example.thanh_toan_asm.dtos.BaseResponse;
import com.example.thanh_toan_asm.dtos.partners.PartnerRequestDtos;
import com.example.thanh_toan_asm.dtos.provices.ResponseProvince;
import com.example.thanh_toan_asm.services.clues.PartnerToClueService;
import com.example.thanh_toan_asm.services.partners.PartnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "update")
    public ResponseEntity<BaseResponse<ResponseProvince>> update(@RequestBody PartnerRequestDtos partnerRequestDtos) {
        return partnerService.updatePartner(partnerRequestDtos);
    }


}
