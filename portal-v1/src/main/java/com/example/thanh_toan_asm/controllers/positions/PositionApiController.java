package com.example.thanh_toan_asm.controllers.positions;

import com.example.thanh_toan_asm.dtos.BaseResponse;
import com.example.thanh_toan_asm.dtos.positions.PositionRequest;
import com.example.thanh_toan_asm.dtos.provices.ResponseProvince;
import com.example.thanh_toan_asm.entitys.Position;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/position/")
@Slf4j
public class PositionApiController {

    private Boolean success;
    private String message;


    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "add")
    public ResponseEntity<BaseResponse<ResponseProvince>> add(@RequestBody PositionRequest positionRequest) {
        try{
            log.info("positionRequest : {}", positionRequest);
            Position position = Position.builder().build();
            success = true;
            message = " Đẩy thành công ";
        } catch (Exception e) {
            success = false;
            message = " Đẩy không thành công!!! ";
            throw new RuntimeException(e);
        }


        return new ResponseEntity<>(new BaseResponse<>(
                success, message, null
        ), HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

}
