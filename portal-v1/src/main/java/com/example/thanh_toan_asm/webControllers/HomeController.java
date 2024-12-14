package com.example.thanh_toan_asm.webControllers;

import com.example.thanh_toan_asm.dtos.BaseResponseList;

import com.example.thanh_toan_asm.dtos.admins.products.ConvertProductDto;
import com.example.thanh_toan_asm.entitys.*;
import com.example.thanh_toan_asm.services.ProductService;
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


    @GetMapping("index")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("dashboard")
    public String dashboard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication :" +authentication);
        return "web/dashboard";
    }

    @GetMapping("web/cart")
    public String cart(Model model){
        return "carts/cart";
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("web/content-home")
    public String contentHome(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        List<Product> products1 = productService.getAllProduct();
        List<ConvertProductDto> convertProductDtos = new ArrayList<>();
        for (Product item : products1) {
            convertProductDtos.add(item.getVo());
        }
        System.out.println(convertProductDtos);
        model.addAttribute("listProduct", convertProductDtos);

        return "mcv/content-home";
    }


    @GetMapping("web/content-home-menu")
    public String header(){
        return "mcv/content-home-menu";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "web/get/productList")
    public ResponseEntity<BaseResponseList<Product>> productList(@RequestParam(defaultValue = "0") String id) {
        return productService.getProductStatus(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("web/list-product")
    public String listProduct(Model model){
        List<Product> products = productService.getAllProduct();
        List<ConvertProductDto> convertProductDtos = new ArrayList<>();
        for (Product item : products) {
            convertProductDtos.add(item.getVo());
        }
        System.out.println(convertProductDtos);
        model.addAttribute("listProduct", convertProductDtos);
        return "admin/products/product";
    }


}
