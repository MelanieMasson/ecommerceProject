package com.mfk.ecommerce.controller;

import com.mfk.ecommerce.entities.ImageEntity;
import com.mfk.ecommerce.entities.ProduitEntity;
import com.mfk.ecommerce.services.ImageService;
import com.mfk.ecommerce.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/images")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @Autowired
    private ProduitService produitService;

    // http://localhost:8080/image
    @GetMapping(value = "")
    public String list( Model model , HttpServletRequest req ){
        String search = req.getParameter("search");
        model.addAttribute("images" , imageService.findAll( search ) );
        model.addAttribute("error" , req.getParameter("error") );
        model.addAttribute("success" , req.getParameter("success") );
        model.addAttribute("search" , search );
        return "image/list_image";
    }

    // http://localhost:8080/image/add
    @GetMapping(value = "/add")
    public String add( Model model ){
        model.addAttribute("produits" , produitService.findAll() );
        model.addAttribute("image" , new ImageEntity() );
        return "image/add_edit";
    }

    @PostMapping(value = "/add")
    public String addPost( HttpServletRequest request , Model model ){
        // Récupération des paramètres envoyés en POST
        String name = request.getParameter("name");
        int produit = Integer.parseInt(request.getParameter("produit"));

        // Préparation de l'entité à sauvegarder
        ProduitEntity prod= new ProduitEntity();
        prod.setId(produit);
        ImageEntity img = new ImageEntity(name,prod );

        // Enregistrement en utilisant la couche service qui gère déjà nos contraintes
        try{
            imageService.addImage( img );
        }catch( Exception e ){
            System.out.println( e.getMessage() );
            model.addAttribute("image" , img );
            model.addAttribute("produits" , produitService.findAll() );
            model.addAttribute("error" , e.getMessage() );
            return "image/add_edit";
        }
        return "redirect:/image?success=true";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit( Model model , @PathVariable int id ){
        model.addAttribute("produits" , produitService.findAll() );
        try {
            model.addAttribute("image", imageService.findImage(id));
        }catch( NoSuchElementException e){
            return "redirect:/image?error=Image%20introuvalble";
        }

        return "image/add_edit";
    }

    @PostMapping(value = "/edit/{id}")
    public String editPost(  Model model , HttpServletRequest request , @PathVariable int id ){
        // Récupération des paramètres envoyés en POST
        String name = request.getParameter("name");
        int produit = Integer.parseInt(request.getParameter("produit"));

        // Préparation de l'entité à sauvegarder
        ProduitEntity prod= new ProduitEntity();
        prod.setId(produit);
        ImageEntity img = new ImageEntity( name,prod);

        // Enregistrement en utilisant la couche service qui gère déjà nos contraintes
        try{
            imageService.editImage( id , img );
        }catch( Exception e ){
            model.addAttribute("produits" , produitService.findAll() );
            System.out.println( e.getMessage() );
            model.addAttribute( "image" , img );
            model.addAttribute("error" , e.getMessage());
            return "image/add_edit";
        }
        return "redirect:/image?success=true";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete( @PathVariable int id ){
        String message = "?success=true";
        try{
            imageService.delete(id);
        }catch( Exception e ){
            message = "?error=Image%20introuvable";
        }

        return "redirect:/image" + message;
    }

    public ImageService getImageService() {
        return imageService;
    }

    public ProduitService getProduitService() {
        return produitService;
    }

    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    public void setProduitService(ProduitService produitService) {
        this.produitService = produitService;
    }
}