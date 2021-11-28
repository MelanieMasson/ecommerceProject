package com.mfk.ecommerce.controller;

import com.mfk.ecommerce.entities.CommandesEntity;
import com.mfk.ecommerce.entities.DetailCommandesEntity;
import com.mfk.ecommerce.service.CommandesService;
import com.mfk.ecommerce.service.DetailCommandesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/commande/detail")
public class DetailCommandesController {

    @Autowired
    private DetailCommandesService dcs;

    @GetMapping(value = "/{id}", produces = "application/json")
    public String getPatient(Model model, @PathVariable("id") int id, HttpServletRequest request) {
        String search = request.getParameter("search");
        String page = (request.getParameter("page") == null) ? "1" : request.getParameter("page");
        model.addAttribute("error", request.getParameter("error"));
        model.addAttribute("success", request.getParameter("success"));
        model.addAttribute("search", search);
        model.addAttribute("id_commande", id);

        Page<DetailCommandesEntity> pages = dcs.findAll(Integer.parseInt(page), id, search);

        model.addAttribute("nombresDetails", pages.getNumberOfElements());
        model.addAttribute("nombrePages", (pages.getTotalPages() == 0) ? 1 : pages.getTotalPages());
        model.addAttribute("details", pages.getContent());
        model.addAttribute("page", page);

        return "commande/detail/list_detail_commande";
    }
}
