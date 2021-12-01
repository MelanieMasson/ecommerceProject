package com.mfk.ecommerce.repositories;

import com.mfk.ecommerce.entities.AdresseEntity;
import com.mfk.ecommerce.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AdresseRepository extends PagingAndSortingRepository<AdresseEntity, Integer> {

    public List<AdresseEntity> findByNameContains(String search );
    public Page<AdresseEntity> findByNameContains(String search , Pageable pageable);
    public Page<AdresseEntity> findAll(Pageable pageable);
}