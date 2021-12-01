package com.mfk.ecommerce.service;

import com.mfk.ecommerce.entities.AdresseEntity;
import com.mfk.ecommerce.entities.CommandesEntity;
import com.mfk.ecommerce.entities.UserEntity;
import com.mfk.ecommerce.repositories.AdresseRepository;
import com.mfk.ecommerce.repositories.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.InvalidObjectException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private UserRepository ur;
    private AdresseRepository ar;

    public UserService(UserRepository ur, AdresseRepository ar ){
        this.ur = ur;
        this.ar = ar;
    }

    public Iterable<UserEntity> findAll() {
        return ur.findAll();
    }

    public Iterable<UserEntity> findAll( String search ) {
        if( search != null && search.length() > 0 ){
            return ur.findByUsernameContainsOrNameContainsOrSurnameContains( search , search , search );
        }
        return ur.findAll();
    }

    public UserEntity findUser(int id){
        try {
            return ur.findById(id).get();
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "User introuvable" );
        }
    }

    public void delete(int id) {
        ur.deleteById(id);
    }

    public static boolean validateEmail(String emailStr) {
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    private void checkUser( UserEntity u ) throws InvalidObjectException {

        if( u.getUsername().length() < 2 ){
            throw new InvalidObjectException("Nom d'utilisateur invalide");
        }
        if( u.getName().length() < 2 ){
            throw new InvalidObjectException("Nom invalide");
        }
        if( u.getSurname().length() < 2 ){
            throw new InvalidObjectException("Prénom invalide");
        }
        if( u.getTelephone().length() <= 8 ){
            throw new InvalidObjectException("Téléphone invalide");
        }
        if( u.getEmail().length() <= 5 || !validateEmail( u.getEmail() ) ){
            throw new InvalidObjectException("Email invalide");
        }
        if( u.getPassword().length() < 8 ){
            throw new InvalidObjectException("Mot de passe trop court, 8 caractères minimum");
        }
    }

    public void addUser(UserEntity u) throws InvalidObjectException {
        checkUser(u);
        ur.save(u);
    }

    public void editUser(int id, UserEntity u) throws InvalidObjectException {
        checkUser(u);
        try{
            UserEntity uExistant = ur.findById(id).get();

            uExistant.setUsername(u.getUsername());
            uExistant.setName(u.getName());
            uExistant.setSurname(u.getSurname());
            uExistant.setEmail(u.getEmail());
            uExistant.setRole(u.getRole());
            uExistant.setTelephone(u.getTelephone());
            uExistant.setActif(u.getActif());
            uExistant.setPassword(u.getPassword());
            uExistant.setAdresses(u.getAdresses());

            ur.save(uExistant);

        }catch (Exception e){
            throw e;
        }
    }
}