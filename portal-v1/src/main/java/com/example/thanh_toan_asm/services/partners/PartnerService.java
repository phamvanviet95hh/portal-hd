package com.example.thanh_toan_asm.services.partners;

import com.example.thanh_toan_asm.repositorys.partners.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartnerService {
    @Autowired
    private PartnerRepository partnerRepository;
}
