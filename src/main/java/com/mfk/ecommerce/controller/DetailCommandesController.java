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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

@Controller
@RequestMapping("/commande/detail")
public class DetailCommandesController {

    @Autowired
    private DetailCommandesService dcs;

    @Autowired
    private CommandesService cs;

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

    @PostMapping(value = "/add")
    public String addPatient(HttpServletRequest request, Model model) {
        DetailCommandesEntity dce = new DetailCommandesEntity();
        populateDetailCommande(dce, request);

        try {
            dcs.save(dce);
        } catch (Exception e) {
            model.addAttribute("detailCommande", dce);
            model.addAttribute("error", e.getMessage());
            return "commande/detail/add_edit";
        }
        return "redirect:/commande?success=true";
    }

    @GetMapping(value = "/delete/")
    public String delete(Model model, HttpServletRequest request) {
        String id_cmd = request.getParameter("id_cmd");
        model.addAttribute("id_cmd", id_cmd);

        try {
            dcs.delete(Integer.parseInt(request.getParameter("id")));
        } catch (Exception e) {
            return "redirect:/commande/detail/" + id_cmd + "?error=Patient%20introuvalble";
        }
        return "redirect:/commande/detail/" + id_cmd;
    }


    private void populateDetailCommande(DetailCommandesEntity dce, HttpServletRequest request) {
        dce.setReduction(Integer.parseInt(request.getParameter("reduction")));
        //  TODO dce.setProduit(ps.findById(Integer.parseInt(request.getParameter("id_produit"))));
        dce.setQuantite(Integer.parseInt(request.getParameter("nom")));
        dce.setPrixUnitaire(Integer.parseInt(request.getParameter("prix_unitaire")));

        if (request.getParameter("id_cmd") != null && request.getParameter("id_cmd") != "") {
            dce.setCommande(cs.findById(Integer.parseInt(request.getParameter("id_cmd"))));
            dce.getCommande().setValueGlobal(dce.getCommande().getValueGlobal() + (dce.getQuantite() * dce.getQuantite()));
        }
        else
        {
            CommandesEntity ce = new CommandesEntity();
            //TODO ce.setUser(us.find((Integer.parseInt(request.getParameter("id_user"))));
            ce.setValueGlobal(dce.getPrixUnitaire() * dce.getQuantite());
            ce.setReduction(dce.getReduction());
            ce.setPrice(ce.getValueGlobal() * (100 - ce.getReduction()));
            cs.save(ce);
            dce.setCommande(ce);
        }
    }
}
