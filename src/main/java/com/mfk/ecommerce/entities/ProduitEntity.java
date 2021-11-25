package com.mfk.ecommerce.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "produit", schema = "ecommerce", catalog = "")
public class ProduitEntity {
    private int id;
    private String name;
    private String description;
    private int prixUnitaire;
    private int quantite;
    private String marque;
    private int promo;
    private byte epuiser;
    private List<ImageEntity> images;
    private UserEntity user;
    private CategorieEntity categorie;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "prix_unitaire")
    public int getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(int prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    @Basic
    @Column(name = "quantite")
    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Basic
    @Column(name = "marque")
    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    @Basic
    @Column(name = "promo")
    public int getPromo() {
        return promo;
    }

    public void setPromo(int promo) {
        this.promo = promo;
    }

    @Basic
    @Column(name = "epuiser")
    public byte getEpuiser() {
        return epuiser;
    }

    public void setEpuiser(byte epuiser) {
        this.epuiser = epuiser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProduitEntity that = (ProduitEntity) o;
        return id == that.id && prixUnitaire == that.prixUnitaire && quantite == that.quantite && promo == that.promo && epuiser == that.epuiser && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(marque, that.marque);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, prixUnitaire, quantite, marque, promo, epuiser);
    }

    @OneToMany(mappedBy = "produit")
    public List<ImageEntity> getImages() {
        return images;
    }

    public void setImages(List<ImageEntity> images) {
        this.images = images;
    }

    @OneToOne
    @JoinColumn(name = "user", referencedColumnName = "id", nullable = false)
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @OneToOne
    @JoinColumn(name = "categorie", referencedColumnName = "id", nullable = false)
    public CategorieEntity getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieEntity categorie) {
        this.categorie = categorie;
    }
}
