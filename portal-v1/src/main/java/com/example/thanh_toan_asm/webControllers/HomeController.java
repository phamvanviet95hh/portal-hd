package com.example.thanh_toan_asm.webControllers;

import com.example.thanh_toan_asm.dtos.BaseResponseList;

import com.example.thanh_toan_asm.dtos.admins.products.ConvertProductDto;
import com.example.thanh_toan_asm.entitys.*;
import com.example.thanh_toan_asm.services.ProductService;
import com.example.thanh_toan_asm.services.partners.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.SpringVersion;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private PartnerService partnerService;


    @GetMapping("index")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("dashboard")
    public String dashboard(Model model) {
        return "web/dashboard";
    }

    @GetMapping("web/cart")
    public String cart(Model model){
        return "carts/cart";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("web/content-home")
    public String contentHome(Model model){
        List<Product> products1 = productService.getAllProduct();
        List<ConvertProductDto> convertProductDtos = new ArrayList<>();
        for (Product item : products1) {
            convertProductDtos.add(item.getVo());
        }
        model.addAttribute("listProduct", convertProductDtos);
        return "mcv/content-home";
    }

    @GetMapping("web/content-home-menu")
    public String header(){
        return "mcv/content-home-menu";
    }

    @GetMapping("web/loadListPartner")
    public String loadListPartner(Model model, @RequestParam String name){
        List<Partner> partners = partnerService.customGetPartHome(name);
        model.addAttribute("partners", partners);
        return "admin/partners/loadListPartnerHome";
    }

    @GetMapping("web/loadInforPartnerHome")
    public String loadInforPartnerHome(Model model, @RequestParam String id){


        return "admin/partners/loadListPartnerHome";
    }


}
