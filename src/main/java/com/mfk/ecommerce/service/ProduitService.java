package com.mfk.ecommerce.service;

import com.mfk.ecommerce.entities.ProduitEntity;
import com.mfk.ecommerce.repositories.ProduitRepository;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.NoSuchElementException;

@Service
public class ProduitService {


    private ProduitRepository produitRepo;
    private UserService userService;
    private ImageService imageService;
   // private VilleRepository vr;


    public ProduitService(ProduitRepository produitRepo, UserService userService, ImageService imageService) {
        this.produitRepo = produitRepo;
        this.userService = userService;
        this.imageService = imageService;
    }

    public Iterable<ProduitEntity> findAll() {
        return produitRepo.findAll();
    }

    public Iterable<ProduitEntity> findAll( String search ) {
        if( search != null && search.length() > 0 ){
            return produitRepo.findByNameContainsOrMarqueContains( search , search );
        }
        return produitRepo.findAll();
    }

    public ProduitEntity findProduit(int id) {
        return produitRepo.findById(id).get();
    }

    public void delete(int id) {
        produitRepo.deleteById(id);
    }
/*
    public static boolean validate(String emailStr) {
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
*/
    /*
    private void checkProduit( ProduitEntity p ) throws InvalidObjectException {

        if( p.getPrenom().length() <= 2 ){
            throw new InvalidObjectException("Prénom invalide");
        }

        if( p.getNom().length() <= 2 ){
            throw new InvalidObjectException("Nom invalide");
        }

        if( p.getAdresse().length() <= 10 ){
            throw new InvalidObjectException("Adresse invalide");
        }

        if( p.getTelephone().length() <= 8 ){
            throw new InvalidObjectException("Téléphone invalide");
        }

        if( p.getEmail().length() <= 5 || !validate( p.getEmail() ) ){
            throw new InvalidObjectException("Email invalide");
        }

        //System.out.println( "Ville passée en param " + p.getVille().getId() );

        try{
            VilleEntity ve = vr.findById(p.getVille().getId()).get();
        }catch( Exception e ){
            throw new InvalidObjectException("Ville invalide");
        }

        VilleEntity ve = vr.findById(p.getVille().getId()).orElseGet( null );
        if( ve == null ){
            throw new InvalidObjectException("Ville invalide");
        }
    }

 */

    public void addProduit(ProduitEntity p) throws InvalidObjectException {
       // checkProduit(p);
        produitRepo.save(p);
    }

    public void editProduit(int id, ProduitEntity p) throws InvalidObjectException {
       // checkProduit(p);

        try{
            ProduitEntity pExistant = produitRepo.findById(id).get();

            pExistant.setName( p.getName() );
            pExistant.setDescription( p.getDescription() );
            pExistant.setPrixUnitaire( p.getPrixUnitaire() );
            pExistant.setQuantite( p.getQuantite() );
            pExistant.setMarque( p.getMarque() );
            pExistant.setPromo( p.getPromo() );
            pExistant.setEpuiser( p.getEpuiser() );
            pExistant.setImages( p.getImages() );
            pExistant.setUser( p.getUser());
            pExistant.setCategorie( p.getCategorie() );

            produitRepo.save( pExistant );

        }catch ( NoSuchElementException e ){
            throw e;
        }
    }
}
