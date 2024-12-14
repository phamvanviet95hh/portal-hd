package com.example.thanh_toan_asm.webControllers.admin;

import com.example.thanh_toan_asm.dtos.admins.products.ConvertProductDto;
import com.example.thanh_toan_asm.entitys.Product;
import com.example.thanh_toan_asm.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("web/admin/")
public class AdminWebController {

    @GetMapping("login")
    public String adminLogin(Model model) {
        return "admin/login";
    }

}
