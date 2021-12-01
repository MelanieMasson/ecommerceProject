package com.mfk.ecommerce.repositories;

import com.mfk.ecommerce.entities.AdresseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AdresseRepository extends PagingAndSortingRepository<AdresseEntity, Integer> {

    public List<AdresseEntity> findByVilleContains(String search );
    public Page<AdresseEntity> findByVilleContains(String search , Pageable pageable);
    public Page<AdresseEntity> findAll(Pageable pageable);
}