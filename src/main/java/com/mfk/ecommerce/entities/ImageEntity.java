package com.mfk.ecommerce.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "image", schema = "ecommerce", catalog = "")
public class ImageEntity {
    private int id;
    private String name;
    private ProduitEntity produit;




    @ManyToOne
    @JoinColumn(name="produit", nullable=false)
    public ProduitEntity getProduit() {
        return produit;
    }

    public void setProduit(ProduitEntity produit) {
        this.produit = produit;
    }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageEntity that = (ImageEntity) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
