package com.mfk.ecommerce.service;

import com.mfk.ecommerce.entities.CategorieEntity;
import com.mfk.ecommerce.repositories.CategorieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.NoSuchElementException;

@Service
public class CategorieService {

    private CategorieRepository categorieRepository;

    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    public Iterable<CategorieEntity> findAll() {
        return categorieRepository.findAll();
    }

    public Iterable<CategorieEntity> findAll(String search) {
        if (search != null && search.length() > 0) {
            return categorieRepository.findByNameContains(search);
        }
        return categorieRepository.findAll();
    }

    public Page<CategorieEntity> findAllByPage(Integer pageNo, Integer pageSize, String search) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        if (search != null && search.length() > 0) {
            return categorieRepository.findByNameContains(search, paging);
        }

        return categorieRepository.findAll(paging);
    }



    private void CheckCategorie( CategorieEntity categorieEntity ) throws InvalidObjectException {

        if( categorieEntity.getName().length() <= 2  ){
            throw new InvalidObjectException("Nom de la catégorie invalide invalide");
        }

        if( categorieEntity.getDescription().length() <= 4 ){
            throw new InvalidObjectException("Description  invalide");
        }

    }

 

    public CategorieEntity findCategorie(int id) {
        return categorieRepository.findById(id).get();
    }

    public void addCategorie(CategorieEntity categorieEntity) throws InvalidObjectException {
          CheckCategorie(categorieEntity);
        categorieRepository.save(categorieEntity);
    }

    public void delete(int id) {
        categorieRepository.deleteById(id);
    }

    public void editCategorie(int id, CategorieEntity categorieEntity) throws InvalidObjectException, NoSuchElementException {
         CheckCategorie(categorieEntity);
        try {
            CategorieEntity categExistante = categorieRepository.findById(id).get();


            categExistante.setName(categorieEntity.getName());
            categExistante.setDescription(categorieEntity.getDescription());
            categorieRepository.save(categExistante);

        } catch (NoSuchElementException e) {
            throw e;
        }

    }


}
