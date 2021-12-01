package com.mfk.ecommerce.service;

import com.mfk.ecommerce.entities.CommandesEntity;
import com.mfk.ecommerce.repositories.CommandesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CommandesService {

    @Autowired
    private CommandesRepository cr;

    public CommandesEntity findById(int id){
        try {
            return cr.findById(id).get();
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Patient introuvable" );
        }
    }

    public Page<CommandesEntity> findAllByPage(int page, String name){
        Pageable p = PageRequest.of(page - 1, 10);

        if (name == null || name.length() == 0)
            return cr.findAll(p);
        else
            return cr.findAllByUser_NameContains(name,p);
    }

    public void save(CommandesEntity c){
        cr.save(c);
    }

    public void update(int id, CommandesEntity c){
        try{
            CommandesEntity cu = this.findById(id);

            cu.setPrice(c.getPrice());
            cu.setReduction(c.getReduction());
            cu.setUser(c.getUser());
            cu.setValueGlobal(c.getValueGlobal());
            cu.setDetails_commandes(c.getDetails_commandes());
            cu.setDateCmd( c.getDateCmd());
            cu.setDateValidation(c.getDateValidation());
            cu.setDateExpedition(c.getDateExpedition());
            cu.setDateReceived(c.getDateReceived());

            cr.save(cu);

        }catch (Exception e){
            throw e;
        }
    }

    public void delete(int id){
        cr.delete(this.findById(id));
    }
}
