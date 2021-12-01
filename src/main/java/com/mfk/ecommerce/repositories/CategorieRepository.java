package com.mfk.ecommerce.repositories;

import com.mfk.ecommerce.entities.CategorieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategorieRepository extends PagingAndSortingRepository<CategorieEntity, Integer> {


    public List<CategorieEntity> findByNameContains(String search );
    public Page<CategorieEntity> findByNameContains(String search , Pageable pageable);

    public Page<CategorieEntity> findAll(Pageable pageable);


/*
    Page<CategorieEntity> findByPublished(boolean published, Pageable pageable);
    Page<CategorieEntity> findByTitleContaining(String nom, Pageable pageable);
    Iterable<CategorieEntity> findAll(Sort sort);

     */
}