package com.mfk.ecommerce.repositories;

import com.mfk.ecommerce.entities.CommandesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface CommandesRepository extends CrudRepository<CommandesEntity, Integer> {

    Page<CommandesEntity> findAll(Pageable pageable);
    Page<CommandesEntity> findAllByUser_NameContains(String name, Pageable pageable);

    CommandesEntity findByDateCmdNotNullAndAndUser_Id(int id);
}
