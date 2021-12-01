package com.mfk.ecommerce.service;

import com.mfk.ecommerce.entities.AdresseEntity;
import com.mfk.ecommerce.repositories.AdresseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.InvalidObjectException;
import java.util.NoSuchElementException;
@Service
public class AdresseService {

    private AdresseRepository ar;

    public AdresseService(AdresseRepository ar) {
        this.ar = ar;
    }

    public Iterable<AdresseEntity> findAll() {
        return ar.findAll();
    }

    public Iterable<AdresseEntity> findAll(String search) {
        if (search != null && search.length() > 0) {
            return ar.findByVilleContains(search);
        }
        return ar.findAll();
    }

    public AdresseEntity findById(int id){
        try {
            return ar.findById(id).get();
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Adresse introuvable" );
        }
    }

    public Page<AdresseEntity> findAllByPage(Integer pageNo, Integer pageSize, String search) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        if (search != null && search.length() > 0) {
            return ar.findByVilleContains(search, paging);
        }

        return ar.findAll(paging);
    }

    private void checkAdresse( AdresseEntity a ) throws InvalidObjectException {

        if( a.getVille().length() < 1  ){
            throw new InvalidObjectException("Nom de ville invalide");
        }
        if( a.getCodePostal().length() < 1  ){
            throw new InvalidObjectException("Code postal invalide");
        }
        if( a.getPays().length() <= 3  ){
            throw new InvalidObjectException("Pays invalide");
        }
    }

    public AdresseEntity findAdresse(int id) {
        return ar.findById(id).get();
    }

    public void addAdresse(AdresseEntity a) throws InvalidObjectException {
        checkAdresse(a);
        ar.save(a);
    }

    public void deleteAdresse(int id) {
        ar.deleteById(id);
    }

    public void editAdresse(int id, AdresseEntity a) throws InvalidObjectException, NoSuchElementException {
        checkAdresse(a);
        try {
            AdresseEntity aExistante = ar.findById(id).get();

            aExistante.setVille(a.getVille());
            aExistante.setCodePostal(a.getCodePostal());
            aExistante.setPays(a.getPays());
            aExistante.setUser(a.getUser());
            ar.save(aExistante);

        } catch (NoSuchElementException e) {
            throw e;
        }

    }

}