package com.example.thanh_toan_asm.controllers.partners;


import com.example.thanh_toan_asm.dtos.BaseResponse;
import com.example.thanh_toan_asm.dtos.partnerToClue.CustomPartnerToClueRequest;
import com.example.thanh_toan_asm.dtos.provices.ResponseProvince;
import com.example.thanh_toan_asm.services.clues.PartnerToClueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/partner-to-clue/")
@Slf4j
public class PartnerToClueApiController {

    @Autowired
    private PartnerToClueService partnerToClueService;


    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "delete")
    public ResponseEntity<BaseResponse<ResponseProvince>> delete(@RequestParam String id, @RequestParam Long idPart) {
        return partnerToClueService.deleteClue(id, idPart);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "add")
    public ResponseEntity<BaseResponse<ResponseProvince>> add(@RequestBody CustomPartnerToClueRequest data) {
        return partnerToClueService.addClueToPartner(data);
    }


}
