package com.mfk.ecommerce.repositories;

import com.mfk.ecommerce.entities.ImageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<ImageEntity,Integer> {

    Iterable<ImageEntity> findByName( String name );

    Iterable<ImageEntity> findByNameContains( String name );

    Iterable<ImageEntity> findByNameContainsOrProduit_NameContainsIgnoreCase( String name , String produitName );

    Iterable<ImageEntity> findByNameContainsOrProduit_NameContains( String name , String produitName );


}
