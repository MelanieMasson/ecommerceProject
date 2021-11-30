package com.mfk.ecommerce.controller;

import com.mfk.ecommerce.entities.CommandesEntity;
import com.mfk.ecommerce.service.CommandesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/commande")
public class CommandesController {

    @Autowired
    private CommandesService cs;


    @GetMapping("")
    public String listPatient(Model model, HttpServletRequest request) {
        String search = request.getParameter("search");
        String page = (request.getParameter("page") == null) ? "1" : request.getParameter("page");
        model.addAttribute("error", request.getParameter("error"));
        model.addAttribute("success", request.getParameter("success"));
        model.addAttribute("search", search);


        Page<CommandesEntity> pages = cs.findAllByPage(Integer.parseInt(page), search);

        model.addAttribute("nombresCommandes", pages.getNumberOfElements());
        model.addAttribute("nombrePages", (pages.getTotalPages() == 0) ? 1 : pages.getTotalPages());
        model.addAttribute("commandes", pages.getContent());
        model.addAttribute("page", page);

        return "commande/list_commande";
    }

    @GetMapping("/delete")
    public String deleteCommande(HttpServletRequest request){
        try {
            cs.delete(Integer.parseInt(request.getParameter("id")));
        } catch (Exception e) {
            return "redirect:/commande/list_commande?error=Patient%20introuvalble";
        }
        return "redirect:/commande/list_commande";
    }

    @GetMapping("/send/{id}")
    public String createCmd(@PathVariable("id") int id){

        return "";
    }
}