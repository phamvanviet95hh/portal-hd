package com.example.thanh_toan_asm.services.partners;

import com.example.thanh_toan_asm.entitys.Partner;
import com.example.thanh_toan_asm.entitys.UserUntity;
import com.example.thanh_toan_asm.repositorys.partners.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartnerService {
    @Autowired
    private PartnerRepository partnerRepository;

    public List<Partner> getAllPartner() {
        return partnerRepository.findAll();
    }


    public Partner findById(Long id) {
        return partnerRepository.findById(id).orElse(null);
    }
}
