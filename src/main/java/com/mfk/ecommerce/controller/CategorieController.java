package com.mfk.ecommerce.controller;


import com.mfk.ecommerce.entities.CategorieEntity;
import com.mfk.ecommerce.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/categorie")
public class CategorieController {
    @Autowired
    private CategorieService categorieService;



    //param page : numéro de la page actuelle
    // size : nbre d'élements par page
    @GetMapping(value = "")
    public String list(Model model, HttpServletRequest request , @RequestParam(name = "page", defaultValue = "0") int page , @RequestParam(name="size", defaultValue="5")int size ){
        String search = request.getParameter("search");

        Page<CategorieEntity> categories = categorieService.findAllByPage(page , size, search);

        model.addAttribute("categories" , categories );
        model.addAttribute( "error" , request.getParameter("error") );
        model.addAttribute( "success" , request.getParameter("success") );
        model.addAttribute( "search" , search );

        model.addAttribute("pageCurrent", page);

        model.addAttribute("pages", new int[categories.getTotalPages()]);

        return "categorie/list_categorie";
    }

    // http://localhost:8080/categorie/add
    @GetMapping(value = "/add")
    public String add( Model model ){
        model.addAttribute("categorie" , new CategorieEntity() );
        return "categorie/add_edit";
    }
    @PostMapping(value = "/add")
    public String addPost( HttpServletRequest request , Model model ){
        // Récupération des paramètres envoyés en POST
        String name = request.getParameter("name");

        String description = request.getParameter("description");
        // Préparation de l'entité à sauvegarder
        CategorieEntity categorieEntity = new CategorieEntity( name , description );
        // Enregistrement en utilisant la couche service qui gère déjà nos contraintes
        try{
            categorieService.addCategorie( categorieEntity );
        }catch( Exception e ){
            System.out.println( e.getMessage() );
            model.addAttribute("categorie" , categorieEntity );
            model.addAttribute("error" , e.getMessage() );
            return "categorie/add_edit";
        }
        return "redirect:/categorie?success=true";
    }
    public CategorieService getVservice() {
        return categorieService;
    }
    @RequestMapping( method = { RequestMethod.GET , RequestMethod.POST} , value = "/edit/{id}" )
    public String editGetPost( Model model , @PathVariable int id ,  HttpServletRequest request ){
        System.out.println( "Add Edit Ville" + request.getMethod() );
        if( request.getMethod().equals("POST") ){
            // Récupération des paramètres envoyés en POST
            String name = request.getParameter("name");

            String description = request.getParameter("description");
            // Préparation de l'entité à sauvegarder
            CategorieEntity categorieEntity = new CategorieEntity(name,description );
            // Enregistrement en utilisant la couche service qui gère déjà nos contraintes
            try{
                categorieService.editCategorie( id , categorieEntity );
            }catch( Exception e ){
                categorieEntity.setId(  -1 ); // hack
                System.out.println( e.getMessage() );
                model.addAttribute("categorie" , categorieEntity );
                model.addAttribute("error" , e.getMessage() );
                return "categorie/add_edit";
            }
            return "redirect:/categorie?success=true";
        }else{
            try{
                model.addAttribute("categorie" , categorieService.findCategorie( id ) );
            }catch ( NoSuchElementException e ){
                return "redirect:/categorie?error=Categorie%20introuvalble";
            }
            return "categorie/add_edit";
        }
    }
    @GetMapping(value = "/delete/{id}")
    public String delete( @PathVariable int id ){
        String message = "?success=true";
        try{
            categorieService.delete(id);
        }catch ( Exception e ){
            message = "?error=Categorie%20introuvalble";
        }
        return "redirect:/categorie"+message;
    }

    public void setCategorieService(CategorieService categorieService) {
        this.categorieService = categorieService;
    }
}