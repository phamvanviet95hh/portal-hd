package com.example.thanh_toan_asm.webControllers.admin.partners;

import com.example.thanh_toan_asm.dtos.partners.CustomPartnerDtos;
import com.example.thanh_toan_asm.dtos.provices.ResponseProvince;
import com.example.thanh_toan_asm.entitys.Partner;
import com.example.thanh_toan_asm.entitys.UserUntity;
import com.example.thanh_toan_asm.services.partners.PartnerService;
import com.example.thanh_toan_asm.services.provinces.ProvinceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("web/partner/")
public class PartnerWebController {

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private ProvinceService provinceService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("list-partner")
    public String listPartner(Model model) {

        List<Partner> partnerList = partnerService.getAllPartner();
        List<CustomPartnerDtos> customPartnerDtos = new ArrayList<>();

        for (Partner item : partnerList){
            customPartnerDtos.add(item.getVo());
        }
        log.info(customPartnerDtos.toString());
        model.addAttribute("customListPartner", customPartnerDtos);
        return "admin/partners/list-partner";
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("editPartner")
    public String editUser(@RequestParam("id") String partnerId, Model model) {
        Long id = Long.valueOf(partnerId);
        Partner partner = partnerService.findById(id);
        List<ResponseProvince> listProvince = provinceService.getListProvinceWeb();
        model.addAttribute("listProvince", listProvince);
        model.addAttribute("partner", partner);
        return "admin/partners/editPartner";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("register")
    public String register(Model model){

        List<ResponseProvince> listProvince = provinceService.getListProvinceWeb();
        model.addAttribute("listProvince", listProvince);
        return "admin/partners/register";
    }

}
