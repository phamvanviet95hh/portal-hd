package com.example.thanh_toan_asm.webControllers.admin.daumoi;


import com.example.thanh_toan_asm.comparator.ServiceComparator;
import com.example.thanh_toan_asm.dtos.partnerToClue.ClueCustomDtos;
import com.example.thanh_toan_asm.dtos.partnerToClue.ClueToPartResponse;
import com.example.thanh_toan_asm.entitys.Clue;
import com.example.thanh_toan_asm.entitys.Position;
import com.example.thanh_toan_asm.services.clues.ClueService;
import com.example.thanh_toan_asm.services.clues.PartnerToClueService;
import com.example.thanh_toan_asm.services.positions.PositionService;
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

    @Autowired
    private PositionService positionService;

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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "list-daumoi")
    public String listDoiMoi(Model model) {
        List<ClueToPartResponse> clueToPartResponses = partnerToClueService.getListClueAndPartAndPosition();

        model.addAttribute("clueToPartResponses", clueToPartResponses);

        return "admin/daumoi/list-dm";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "add-clue")
    public String addClue(Model model) {
        List<Position> positions = positionService.getListPosition();
        model.addAttribute("positions", positions);
        return "admin/daumoi/add-dm";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "edit-clue")
    public String addClue(Model model, @RequestParam String id) {
        Long idClue = id != null ? Long.valueOf(id) : null;
        ClueCustomDtos clueCustomDtos = clueService.getOneClueCustom(idClue);
        List<Position> positions = positionService.getListPosition();
        model.addAttribute("positions", positions);
        model.addAttribute("clueCustomDtos", clueCustomDtos);
        return "admin/daumoi/edit-dm";
    }

}
