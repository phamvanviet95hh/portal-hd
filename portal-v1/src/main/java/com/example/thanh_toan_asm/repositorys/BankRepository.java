package com.example.thanh_toan_asm.repositorys;

import com.example.thanh_toan_asm.entitys.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    Bank getByNameBank(String nameBank);
}
