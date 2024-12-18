package com.example.thanh_toan_asm.services.clues;

import com.example.thanh_toan_asm.dtos.BaseResponse;
import com.example.thanh_toan_asm.dtos.GlobalValue;
import com.example.thanh_toan_asm.dtos.partnerToClue.ClueCustomDtos;
import com.example.thanh_toan_asm.dtos.partnerToClue.CustomPartnerToClueRequest;
import com.example.thanh_toan_asm.dtos.provices.ResponseProvince;
import com.example.thanh_toan_asm.entitys.Clue;
import com.example.thanh_toan_asm.entitys.Partner;
import com.example.thanh_toan_asm.entitys.PartnerToClue;
import com.example.thanh_toan_asm.repositorys.clues.PartnerToClueRepository;
import com.example.thanh_toan_asm.services.partners.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartnerToClueService {

    private Boolean success;
    private String message;


    @Autowired
    private PartnerToClueRepository partnerToClueRepository;

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private ClueService clueService;

    public List<ClueCustomDtos> getListClue(Long id) {

        return partnerToClueRepository.findByPartner(id);

    }

    public ResponseEntity<BaseResponse<ResponseProvince>> deleteClue(String id, Long partnerId) {

        try{
            partnerToClueRepository.deleteByClue(id, partnerId);
            success = true;
            message = GlobalValue.msgDeleteSuccess;
        }catch (Exception e){
            success = false;
            message = GlobalValue.msgDeleteFail;
            e.printStackTrace();
        }

        return new ResponseEntity<>(new BaseResponse<>(
                success, message, null
        ), HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    public ResponseEntity<BaseResponse<ResponseProvince>> addClueToPartner(CustomPartnerToClueRequest data) {

        try {
            if (data.getIdPart() != null && !data.getData().isEmpty()) {
                for (Long item : data.getData()){
                    PartnerToClue partnerToClue = partnerToClueRepository.customGetClueAndPartner(data.getIdPart(), item);
                    if(partnerToClue != null){
                        success = false;
                        message = "Đã tồn tại đầu mối này trong danh sách";
                        return new ResponseEntity<>(new BaseResponse<>(
                                success, message, null
                        ), HttpStatusCode.valueOf(HttpStatus.OK.value()));
                    }else {
                        Partner partner = partnerService.findById(data.getIdPart());
                        Clue clue = clueService.getOneClue(item);
                        PartnerToClue partnerToClueNew = PartnerToClue.builder()
                                .partner(partner)
                                .clue(clue)
                                .build();
                        partnerToClueRepository.save(partnerToClueNew);
                        success = true;
                        message = GlobalValue.msgSuccessImport;
                    }
                }
            }else {
                success = false;
                message = "Dữ liệu truyền lên rỗng";
                return new ResponseEntity<>(new BaseResponse<>(
                        success, message, null
                ), HttpStatusCode.valueOf(HttpStatus.OK.value()));
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(new BaseResponse<>(
                success, message, null
        ), HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }
}
