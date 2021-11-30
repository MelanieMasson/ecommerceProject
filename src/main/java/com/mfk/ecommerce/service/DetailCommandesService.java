package com.mfk.ecommerce.service;

import com.mfk.ecommerce.entities.DetailCommandesEntity;
import com.mfk.ecommerce.repositories.DetailCommandesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DetailCommandesService {

    @Autowired
    private DetailCommandesRepository dcr;

    public DetailCommandesEntity findById(int id){
        try {
            return dcr.findById(id).get();
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Patient introuvable" );
        }
    }

    public Page<DetailCommandesEntity> findAll(int page, int cmd, String name){
        Pageable p = PageRequest.of(page - 1, 10);

        if (name == null || name.length() == 0)
            return dcr.findAllByCommande_Id(p, cmd);
        else
            return dcr.findAllByCommande_IdAndProduit_Name(p, cmd, name);
    }

    public void save(DetailCommandesEntity c){
        dcr.save(c);
    }

    public void update(int id, DetailCommandesEntity dc){
        try{
            DetailCommandesEntity dcu = this.findById(id);

            dcu.setCommande(dc.getCommande());
            dcu.setReduction(dc.getReduction());
            dcu.setProduit(dc.getProduit());
            dcu.setQuantite(dc.getQuantite());
            dcu.setPrixUnitaire(dc.getPrixUnitaire());

            dcr.save(dcu);

        }catch (Exception e){
            throw e;
        }
    }

    public void delete(int id){
        dcr.delete(this.findById(id));
    }
}