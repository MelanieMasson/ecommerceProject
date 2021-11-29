package com.mfk.ecommerce.controller;

import com.mfk.ecommerce.entities.CategorieEntity;
import com.mfk.ecommerce.entities.ProduitEntity;
import com.mfk.ecommerce.entities.UserEntity;
import com.mfk.ecommerce.services.CategorieService;
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
@RequestMapping("/produit")
public class ProduitController {

        @Autowired
        private ProduitService produitService;
        
    @Autowired
    private UserService userService;
    @Autowired
    private ImageService imageService;

        @Autowired
        private CategorieService categorieService;

        // http://localhost:8080/produit
        @GetMapping(value = "")
        public String list(Model model , HttpServletRequest req ){
            String search = req.getParameter("search");
            model.addAttribute("produits" , produitService.findAll( search ) );
            model.addAttribute("error" , req.getParameter("error") );
            model.addAttribute("success" , req.getParameter("success") );
            model.addAttribute("search" , search );
            return "produit/list_produit";
        }

        // http://localhost:8080/produit/add
        @GetMapping(value = "/add")
        public String add( Model model ){
            model.addAttribute("categories" , categorieService.findAll() );
           // model.addAttribute("users" , userService.findAll() );
          //  model.addAttribute("listImages" , imageService.findAll() );
            model.addAttribute("produit" , new ProduitEntity() );
            return "produit/add_edit";
        }

        @PostMapping(value = "/add")
        public String addPost( HttpServletRequest request , Model model ){
            // Récupération des paramètres envoyés en POST
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            Double prixUnitaire =Double.parseDouble( request.getParameter("prixUnitaire"));
            int quantite =Integer.parseInt( request.getParameter("quantite"));
            String marque = request.getParameter("marque");
            Double promo = Double.parseDouble(request.getParameter("promo"));
            byte epuiser =Byte.parseByte(request.getParameter("epuiser"));
            /**
             *  TODO rjouter la collection des images dans produits 
             */
            int images = Integer.parseInt(request.getParameter("images"));
            int userId = Integer.parseInt(request.getParameter("userId"));
            int categorieId = Integer.parseInt(request.getParameter("categorieId"));

            // Préparation de l'entité à sauvegarder
            CategorieEntity cat = new CategorieEntity();
            cat.setId(categorieId);
            UserEntity user = new UserEntity();
            user.setId(userId);

            ProduitEntity produit= new ProduitEntity(  name , description , prixUnitaire  , quantite , marque , promo,epuiser,user,cat);
            /**
             *  TODO rjouter la collection des images dans produits 
             */
            // ProduitEntity produit= new ProduitEntity( 0 , name , description , prixUnitaire  , quantite , cat , marque , promo,epuiser,images,user,categorieId);

            // Enregistrement en utilisant la couche service qui gère déjà nos contraintes
            try{
                produitService.addProduit( produit);
            }catch( Exception e ){
                System.out.println( e.getMessage() );

                model.addAttribute("produit" , produit);
                /**
                 *  TODO rjouter la collection des images dans produits
                 */
                //model.addAttribute("images" , images);
                model.addAttribute("users" , UserService.findAll() );
                model.addAttribute("categories" , categorieService.findAll() );
                model.addAttribute("error" , e.getMessage() );
                return "produit/add_edit";
            }
            return "redirect:/produit?success=true";
        }

        @GetMapping(value = "/edit/{id}")
        public String edit( Model model , @PathVariable int id ){
            model.addAttribute("categories" , categorieService.findAll() );
            try {
                model.addAttribute("produit", produitService.findProduit(id));
            }catch( NoSuchElementException e){
                return "redirect:/produit?error=Produit%20introuvalble";
            }

            return "produit/add_edit";
        }

        @PostMapping(value = "/edit/{id}")
        public String editPost(  Model model , HttpServletRequest request , @PathVariable int id ){
            // Récupération des paramètres envoyés en POST
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            Double prixUnitaire = Double.parseDouble(request.getParameter("prixUnitaire"));
            int quantite =Integer.parseInt( request.getParameter("quantite"));
            String marque = request.getParameter("marque");
           Double promo = Double.parseDouble(request.getParameter("promo"));
            Byte epuiser = Byte.parseByte(request.getParameter("epuiser"));
            int images = Integer.parseInt(request.getParameter("images"));
            int userId = Integer.parseInt(request.getParameter("userId"));
            int categorieId = Integer.parseInt(request.getParameter("categorieId"));

            // Préparation de l'entité à sauvegarder
            CategorieEntity cat = new CategorieEntity();
            cat.setId(categorieId);
            UserEntity user = new UserEntity();
            user.setId(userId);
            ProduitEntity produit= new ProduitEntity( name , description , prixUnitaire  , quantite , marque , promo,epuiser,user,cat );

            // Enregistrement en utilisant la couche service qui gère déjà nos contraintes
            try{
                produitService.editProduit( id , produit);
            }catch( Exception e ){
                model.addAttribute("categories" , categorieService.findAll() );
                System.out.println( e.getMessage() );
                model.addAttribute( "produit" , produit);
                model.addAttribute("error" , e.getMessage());
                return "produit/add_edit";
            }
            return "redirect:/produit?success=true";
        }

        @GetMapping(value = "/delete/{id}")
        public String delete( @PathVariable int id ){
            String message = "?success=true";
            try{
                produitService.delete(id);
            }catch( Exception e ){
                message = "?error=Produit%20introuvable";
            }

            return "redirect:/produit" + message;
        }

        public ProduitService getPs() {
            return produitService;
        }

        public void setPs(ProduitService produitService) {
            this.produitService = produitService;
        }

    }