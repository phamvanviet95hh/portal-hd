package com.example.thanh_toan_asm.services.clues;

import com.example.thanh_toan_asm.dtos.BaseResponse;
import com.example.thanh_toan_asm.dtos.GlobalValue;
import com.example.thanh_toan_asm.dtos.clues.ClueAddRequest;
import com.example.thanh_toan_asm.dtos.partnerToClue.ClueCustomDtos;
import com.example.thanh_toan_asm.entitys.Clue;
import com.example.thanh_toan_asm.entitys.Position;
import com.example.thanh_toan_asm.repositorys.clues.ClueRepository;
import com.example.thanh_toan_asm.repositorys.clues.PartnerToClueRepository;
import com.example.thanh_toan_asm.repositorys.positions.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClueService {

    private Boolean success;
    private String message;

    @Autowired
    private ClueRepository clueRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private PartnerToClueRepository partnerToClueRepository;

    public List<Clue> getAllClue() {
        return clueRepository.findAll();
    }

    public Clue getOneClue(Long idClue) {
        return clueRepository.getOneClue(idClue);
    }

    public ResponseEntity<BaseResponse<ClueCustomDtos>> deleteClue(String id) {

        Long idClue = id != null ? Long.parseLong(id) : null;
        if(idClue != null) {
            try{
                partnerToClueRepository.deleteByClueCustom(idClue);
                clueRepository.deleteById(idClue);
                success = true;
                message = GlobalValue.msgDeleteSuccess;
            } catch (Exception e) {
                success = false;
                message = GlobalValue.msgDeleteFail;
                throw new RuntimeException(e);
            }
        }
        return new ResponseEntity<>(new BaseResponse<>(
                success, message, null
        ), HttpStatusCode.valueOf(HttpStatus.OK.value()));

    }

    public ResponseEntity<BaseResponse<ClueCustomDtos>> addClue(ClueAddRequest clueAddRequest) {

        if (clueAddRequest != null) {
            try {
                Clue clue = clueRepository.findByEmailClue(clueAddRequest.getEmailClue());
                if (clue != null) {
                    success = false;
                    message = "Đã tồn tại đầu mối này trong hệ thống";
                    return new ResponseEntity<>(new BaseResponse<>(
                            success, message, null
                    ), HttpStatusCode.valueOf(HttpStatus.OK.value()));
                }else {
                    Position position = positionRepository.findById(clueAddRequest.getPositionClue()).get();
                    Clue newClue = Clue.builder()
                            .nameClue(clueAddRequest.getClueName())
                            .phoneClue(clueAddRequest.getPhoneClue())
                            .emailClue(clueAddRequest.getEmailClue())
                            .positions(position)
                            .build();
                    clueRepository.save(newClue);
                    success = true;
                    message = GlobalValue.msgSuccessImport;
                }
            }catch (Exception e) {
                success = false;
                message = GlobalValue.msgFailImport;
                e.printStackTrace();
            }
        }else {
            success = false;
            message = "Dữ liệu truyền lên trỗng";
        }

        return new ResponseEntity<>(new BaseResponse<>(
                success, message, null
        ), HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    public ClueCustomDtos getOneClueCustom(Long idClue) {
        return clueRepository.getOneClueCustom(idClue);
    }

    public ResponseEntity<BaseResponse<ClueCustomDtos>> updateClue(ClueAddRequest clueAddRequest) {

        if (clueAddRequest != null) {
            try {
                Clue clue = clueRepository.findByEmailClue(clueAddRequest.getEmailClue());
                if (clue != null) {
                    Position position = positionRepository.findById(clueAddRequest.getPositionClue()).get();
                    Clue newClue = Clue.builder()
                            .nameClue(clueAddRequest.getClueName())
                            .phoneClue(clueAddRequest.getPhoneClue())
                            .emailClue(clueAddRequest.getEmailClue())
                            .positions(position)
                            .build();
                    clueRepository.save(newClue);
                    success = true;
                    message = GlobalValue.msgSuccessImport;

                }else {
                    success = false;
                    message = "Không tồn tại đầu mối này trong hệ thống";
                    return new ResponseEntity<>(new BaseResponse<>(
                            success, message, null
                    ), HttpStatusCode.valueOf(HttpStatus.OK.value()));
                }
            }catch (Exception e) {
                success = false;
                message = GlobalValue.msgFailImport;
                e.printStackTrace();
            }
        }else {
            success = false;
            message = "Dữ liệu truyền lên trỗng";
        }

        return new ResponseEntity<>(new BaseResponse<>(
                success, message, null
        ), HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }
}
