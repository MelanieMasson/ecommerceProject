package com.mfk.ecommerce.repositories;

import com.mfk.ecommerce.entities.ProduitEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository  extends CrudRepository<ProduitEntity,Integer> {

    Iterable<ProduitEntity> findByName( String name );

    Iterable<ProduitEntity> findByNameContains( String name );

    Iterable<ProduitEntity> findByNameContainsOrMarqueContainsIgnoreCase( String name , String marque );

    Iterable<ProduitEntity> findByNameContainsOrMarqueContains( String name , String marque );


}
