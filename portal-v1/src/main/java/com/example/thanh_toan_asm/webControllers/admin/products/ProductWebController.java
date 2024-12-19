package com.example.thanh_toan_asm.webControllers.admin.products;

import com.example.thanh_toan_asm.dtos.BaseResponseList;
import com.example.thanh_toan_asm.dtos.admins.products.ConvertProductDto;
import com.example.thanh_toan_asm.entitys.Product;
import com.example.thanh_toan_asm.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("web/product/")
public class ProductWebController {
    @Autowired
    private ProductService productService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("addProduct")
    public String addProduct(Model model) {

        return "admin/products/addProduct";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("editProduct")
    public String editProduct(Model model, @RequestParam("id") Long id) {
        Product product = productService.getProduct(id);
        model.addAttribute("product", product);
        return "admin/products/editProduct";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "get/productList")
    public ResponseEntity<BaseResponseList<Product>> productList(@RequestParam(defaultValue = "0") String id) {
        return productService.getProductStatus(id);
    }



    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("list-product")
    public String listProduct(Model model){
        List<Product> products = productService.getAllProduct();
        List<ConvertProductDto> convertProductDtos = new ArrayList<>();
        for (Product item : products) {
            convertProductDtos.add(item.getVo());
        }
        model.addAttribute("listProduct", convertProductDtos);
        return "admin/products/product";
    }
}
