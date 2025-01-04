package com.example.thanh_toan_asm.services;

import com.example.thanh_toan_asm.repositorys.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

}
