package com.example.thanh_toan_asm.services.partners;

import com.example.thanh_toan_asm.confignations.SystemBe;
import com.example.thanh_toan_asm.dtos.BaseResponse;
import com.example.thanh_toan_asm.dtos.GlobalValue;
import com.example.thanh_toan_asm.dtos.partners.PartnerRequestDtos;
import com.example.thanh_toan_asm.dtos.partners.ResponsePartnerDtos;
import com.example.thanh_toan_asm.dtos.provices.ResponseProvince;
import com.example.thanh_toan_asm.entitys.*;
import com.example.thanh_toan_asm.repositorys.BankRepository;
import com.example.thanh_toan_asm.repositorys.partners.PartnerRepository;
import com.example.thanh_toan_asm.repositorys.positions.PositionRepository;
import com.example.thanh_toan_asm.repositorys.provices.ProvincesRepository;
import com.example.thanh_toan_asm.repositorys.wards.WardsRepository;
import com.example.thanh_toan_asm.services.districts.DistrictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class PartnerService {
    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private ProvincesRepository provincesRepository;

    @Autowired
    private WardsRepository wardsRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private BankRepository bankRepository;


    private Boolean success;
    private String message;

    @Autowired
    private DistrictService districtService;

    public List<Partner> getAllPartner() {
        return partnerRepository.findAll();
    }


    public Partner findById(Long id) {
        return partnerRepository.findById(id).orElse(null);
    }

    public ResponseEntity<BaseResponse<ResponseProvince>> addPartner(PartnerRequestDtos partnerRequestDtos) {

        try{
            Districts districts = districtService.getOneDistrict(partnerRequestDtos.getIdHuyen());
            Province province = provincesRepository.getByCode(partnerRequestDtos.getIdTinh());
            Position position = positionRepository.getById(Long.valueOf(partnerRequestDtos.getPosition()));
            Bank bank = bankRepository.getByNameBank(partnerRequestDtos.getNameBank());
            Ward ward = wardsRepository.getByCode(partnerRequestDtos.getIdXa());
            Partner partner = Partner.builder()
                    .position(position.getPositionName())
                    .bank(bank)
                    .mst(partnerRequestDtos.getMst())
                    .createAt(LocalDateTime.now())
                    .updateAt(LocalDateTime.now())
                    .email(partnerRequestDtos.getEmail())
                    .phone(partnerRequestDtos.getPhone())
                    .stk(partnerRequestDtos.getStk())
                    .districts(districts)
                    .fullName(partnerRequestDtos.getNameNDD())
                    .gender(partnerRequestDtos.getGender())
                    .nameCompany(partnerRequestDtos.getNameCompany())
                    .province(province)
                    .positions(position)
                    .ward(ward)
                    .build();

            Partner partnerNew = partnerRepository.save(partner);
            success = true;
            message = " Khởi tạo khách hàng mới thành công";
        } catch (Exception e) {
            success = false;
            message = " Khởi tạo khách hàng mới không thành công!!! ";
            throw new RuntimeException(e);
        }
        log.info("Partner : {}", message);

        return new ResponseEntity<>(new BaseResponse<>(
                success, message, null
        ), HttpStatusCode.valueOf(HttpStatus.OK.value()));

    }

    public ResponseEntity<BaseResponse<ResponseProvince>> updatePartner(PartnerRequestDtos partnerRequestDtos) {

        if(partnerRequestDtos != null){
            try{
                Partner partner = partnerRepository.findById(partnerRequestDtos.getPartnerId()).get();
                BeanUtils.copyProperties(partnerRequestDtos, partner, SystemBe.getNullPropertyNames(partnerRequestDtos));
                partner.setUpdateAt(LocalDateTime.now());
                partnerRepository.save(partner);
                success = true;
                message = GlobalValue.msgUpdateSuccess;
            } catch (Exception e) {
                success = false;
                message = GlobalValue.msgUpdateFail;
                throw new RuntimeException(e);
            }
        }else {
            success = false;
            message = "Data gửi lên rỗng!!!";
        }

        return new ResponseEntity<>(new BaseResponse<>(
                success, message, null
        ), HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    public List<Partner> customGetPartHome(String name) {
        return partnerRepository.customGetPartHome(name);
    }

    public ResponseEntity<BaseResponse<ResponsePartnerDtos>> getInForA(PartnerRequestDtos partnerRequestDtos) {
        Long idpart = partnerRequestDtos.getPartnerId() != null ? partnerRequestDtos.getPartnerId() : null;
        assert idpart != null;
        ResponsePartnerDtos responsePartnerDtos = null;
        try {
            success = true;
            message = "Lấy thông tin đầu mối";
            Partner partner  = partnerRepository.findById(idpart).orElse(null);
            responsePartnerDtos  = ResponsePartnerDtos.builder()
                    .nameBank(partner.getBank().getNameBank())
                    .addressBank(partner.getBank().getProvince().getCode() + " - " + partner.getBank().getDistricts().getCode() + " - " + partner.getBank().getWard().getCode())
                    .nameNDD(partner.getFullName())
                    .addressKh(partner.getProvince().getCode() + " - " + partner.getDistricts().getCode() + " - " + partner.getWard().getCode())
                    .mst(partner.getMst())
                    .gender(partner.getGender())
                    .stk(partner.getStk())
                    .nameCompany(partner.getNameCompany())
                    .position(partner.getPositions().getPositionName())
                    .email(partner.getEmail())
                    .phone(partner.getPhone())
                    .build();
        } catch (Exception e) {
            success = false;
            message = "Lấy thông tin đầu mối không thành công";
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(new BaseResponse<>(
                success, message, responsePartnerDtos
        ), HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }
}
