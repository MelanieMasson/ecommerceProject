package com.mfk.ecommerce.service;

import com.mfk.ecommerce.entities.ImageEntity;
import com.mfk.ecommerce.repositories.ImageRepository;
import com.mfk.ecommerce.repositories.ProduitRepository;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ImageService {
    
        private ImageRepository imageRepo;
        private ProduitRepository produitRepo;

        public ImageService(ImageRepository imageRepo, ProduitRepository produitRepo ){
            this.imageRepo = imageRepo;
            this.produitRepo = produitRepo;
        }

        public Iterable<ImageEntity> findAll() {
            return imageRepo.findAll();
        }

        public Iterable<ImageEntity> findAll( String search ) {
            if( search != null && search.length() > 0 ){
                return imageRepo.findByNameContainsOrProduit_NameContains( search , search );
            }
            return imageRepo.findAll();
        }

        public ImageEntity findImage(int id) {
            return imageRepo.findById(id).get();
        }

        public void delete(int id) {
            imageRepo.deleteById(id);
        }
/*
        public static boolean validate(String emailStr) {
            Pattern VALID_EMAIL_ADDRESS_REGEX =
                    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
            return matcher.find();
        }

        private void checkImage( ImageEntity p ) throws InvalidObjectException {

            if( img.getPrenom().length() <= 2 ){
                throw new InvalidObjectException("Prénom invalide");
            }

            if( img.getNom().length() <= 2 ){
                throw new InvalidObjectException("Nom invalide");
            }

            if( img.getAdresse().length() <= 10 ){
                throw new InvalidObjectException("Adresse invalide");
            }

            if( img.getTelephone().length() <= 8 ){
                throw new InvalidObjectException("Téléphone invalide");
            }

            if( img.getEmail().length() <= 5 || !validate( img.getEmail() ) ){
                throw new InvalidObjectException("Email invalide");
            }

            //System.out.println( "Produit passée en param " + img.getProduit().getId() );

        try{
            ProduitEntity ve = produitRepo.findById(img.getProduit().getId()).get();
        }catch( Exception e ){
            throw new InvalidObjectException("Produit invalide");
        }
        
 

            ProduitEntity ve = produitRepo.findById(img.getProduit().getId()).orElseGet( null );
            if( ve == null ){
                throw new InvalidObjectException("Produit invalide");
            }
        }
*/
        public void addImage(ImageEntity img) throws InvalidObjectException {
        //    checkImage(img);
            imageRepo.save(img);
        }

        public void editImage(int id, ImageEntity img) throws InvalidObjectException {
        //    checkImage(img);

        /*Optional<ImageEntity> pe = imageRepo.findById(id);
        ImageEntity pp = pe.orElse( null );*/

            try{
                ImageEntity imgExistant = imageRepo.findById(id).get();


                imgExistant.setName( img.getName() );
                imgExistant.setProduit( img.getProduit() );


                imageRepo.save( imgExistant );

            }catch ( NoSuchElementException e ){
                throw e;
            }
        }
    }
