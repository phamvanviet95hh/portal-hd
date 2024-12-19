package com.example.thanh_toan_asm.controllers.daumois;

import com.example.thanh_toan_asm.dtos.BaseResponse;
import com.example.thanh_toan_asm.dtos.clues.ClueAddRequest;
import com.example.thanh_toan_asm.dtos.partnerToClue.ClueCustomDtos;
import com.example.thanh_toan_asm.services.clues.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/clue/")
public class ClueApiController {

    @Autowired
    private ClueService clueService;

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("delete")
    public ResponseEntity<BaseResponse<ClueCustomDtos>> delete(@RequestParam String id){
        return clueService.deleteClue(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("add")
    public ResponseEntity<BaseResponse<ClueCustomDtos>> add(@RequestBody ClueAddRequest clueAddRequest){
        return clueService.addClue(clueAddRequest);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("update")
    public ResponseEntity<BaseResponse<ClueCustomDtos>> update(@RequestBody ClueAddRequest clueAddRequest){
        return clueService.updateClue(clueAddRequest);
    }

}
