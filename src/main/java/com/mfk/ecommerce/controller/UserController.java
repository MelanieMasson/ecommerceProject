package com.mfk.ecommerce.controller;

import com.mfk.ecommerce.entities.AdresseEntity;
import com.mfk.ecommerce.entities.UserEntity;
import com.mfk.ecommerce.service.UserService;
import com.mfk.ecommerce.service.AdresseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService us;

    @Autowired
    private AdresseService aservice;

    // http://localhost:8080/user
    @GetMapping("")
    public String list( Model model , HttpServletRequest req ){
        String search = req.getParameter("search");
        model.addAttribute("user" , us.findAll( search ) );
        model.addAttribute("error" , req.getParameter("error") );
        model.addAttribute("success" , req.getParameter("success") );
        model.addAttribute("search" , search );
        return "user/list_user";
    }

    // http://localhost:8080/user/add
    @GetMapping(value = "/add")
    public String add( Model model ){
        model.addAttribute("adresses" , aservice.findAll() );
        model.addAttribute("user" , new UserEntity() );
        return "user/add_edit";
    }

    @PostMapping(value = "/add")
    public String addPost( HttpServletRequest request , Model model ){
        // Récupération des paramètres envoyés en POST
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        String telephone = request.getParameter("telephone");
        String actif = request.getParameter("actif");
        String password = request.getParameter("password");
        int adresses = Integer.parseInt(request.getParameter("adresses"));

        // Préparation de l'entité à sauvegarder
        AdresseEntity a = new AdresseEntity();
        a.setId(adresses);
        UserEntity u = new UserEntity( 0 , username , name , surname , email , role , telephone , actif , password, a );

        // Enregistrement en utilisant la couche service qui gère déjà nos contraintes
        try{
            us.addUser( u );
        }catch( Exception e ){
            System.out.println( e.getMessage() );
            model.addAttribute("user" , u );
            model.addAttribute("adresses" , aservice.findAll() );
            model.addAttribute("error" , e.getMessage() );
            return "user/add_edit";
        }
        return "redirect:/user?success=true";
    }

    // http://localhost:8080/user/edit/{id}
    @GetMapping(value = "/edit/{id}")
    public String edit( Model model , @PathVariable int id ){
        model.addAttribute("adresses" , aservice.findAll() );
        try {
            model.addAttribute("user", us.findUser(id));
        }catch( NoSuchElementException e){
            return "redirect:/user?error=User%20introuvalble";
        }

        return "user/add_edit";
    }

    @PostMapping(value = "/edit/{id}")
    public String editPost(  Model model , HttpServletRequest request , @PathVariable int id ){
        // Récupération des paramètres envoyés en POST
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        String telephone = request.getParameter("telephone");
        String actif = request.getParameter("actif");
        String password = request.getParameter("password");
        int adresses = Integer.parseInt(request.getParameter("adresses"));

        // Préparation de l'entité à sauvegarder
        AdresseEntity a = new AdresseEntity();
        a.setId(adresses);
        UserEntity u = new UserEntity( 0 , username , name , surname, email, role, telephone, actif, password, a);

        // Enregistrement en utilisant la couche service qui gère déjà nos contraintes
        try{
            us.editUser( id , u );
        }catch( Exception e ){
            model.addAttribute("adresses" , aservice.findAll() );
            System.out.println( e.getMessage() );
            model.addAttribute( "user" , u );
            model.addAttribute("error" , e.getMessage());
            return "user/add_edit";
        }
        return "redirect:/user?success=true";
    }

    // http://localhost:8080/user/add
    @GetMapping(value = "/delete/{id}")
    public String delete( @PathVariable int id ){
        String message = "?success=true";
        try{
            us.delete(id);
        }catch( Exception e ){
            message = "?error=User%20introuvable";
        }
        return "redirect:/user" + message;
    }

    public UserService getUs() {return us;}

    public void setUs(UserService us) {
        this.us = us;
    }
}