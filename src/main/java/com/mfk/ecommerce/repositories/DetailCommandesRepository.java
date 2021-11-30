package com.mfk.ecommerce.repositories;

import com.mfk.ecommerce.entities.CommandesEntity;
import com.mfk.ecommerce.entities.DetailCommandesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface DetailCommandesRepository extends CrudRepository<DetailCommandesEntity, Integer> {

    Page<DetailCommandesEntity> findAllByCommande_Id(Pageable pageable, int cmd);
    Page<DetailCommandesEntity> findAllByCommande_IdAndProduit_Name(Pageable pageable, int cmd, String name);
}