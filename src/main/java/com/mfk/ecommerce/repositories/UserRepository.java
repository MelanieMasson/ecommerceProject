package com.mfk.ecommerce.repositories;

import com.mfk.ecommerce.entities.AdresseEntity;
import com.mfk.ecommerce.entities.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity , Integer> {

    Iterable<UserEntity> findByName(String nom); // select * from patient where nom = :nom

    Iterable<UserEntity> findByNameContains(String nom); // select * from patient where nom like :nom

    Iterable<UserEntity> findByUsernameContainsOrNameContainsOrSurnameContains(String username, String name, String surname);
}
