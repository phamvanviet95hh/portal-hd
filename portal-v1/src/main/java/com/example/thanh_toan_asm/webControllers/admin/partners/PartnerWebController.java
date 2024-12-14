package com.example.thanh_toan_asm.webControllers.admin.partners;

import com.example.thanh_toan_asm.services.partners.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("web/partner/")
public class PartnerWebController {

    @Autowired
    private PartnerService partnerService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("list-partner")
    public String listPartner(Model model) {
        return "admin/partners/list-partner";
    }

}
