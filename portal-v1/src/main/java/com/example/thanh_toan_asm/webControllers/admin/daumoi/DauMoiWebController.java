package com.example.thanh_toan_asm.webControllers.admin.daumoi;


import com.example.thanh_toan_asm.comparator.ServiceComparator;
import com.example.thanh_toan_asm.dtos.partnerToClue.ClueCustomDtos;
import com.example.thanh_toan_asm.entitys.Clue;
import com.example.thanh_toan_asm.services.clues.ClueService;
import com.example.thanh_toan_asm.services.clues.PartnerToClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "web/daumoi/")
public class DauMoiWebController {

    @Autowired
    private ClueService clueService;

    @Autowired
    private PartnerToClueService partnerToClueService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "get/dm-list")
    public String loadDoiMoi(Model model, @RequestParam String id) {

        Long idPartner = id != null ? Long.valueOf(id) : null;
        List<Clue> clues = clueService.getAllClue();
        List<ClueCustomDtos> clueCustomDtos = partnerToClueService.getListClue(idPartner);

        model.addAttribute("clues", clues);
        model.addAttribute("clueCustomDtos", clueCustomDtos);
        model.addAttribute("productComparator", new ServiceComparator());
        model.addAttribute("partnerId", idPartner);

        return "admin/daumoi/loadListDm";
    }

}
