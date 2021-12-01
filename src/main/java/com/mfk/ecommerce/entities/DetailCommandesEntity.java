package com.mfk.ecommerce.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "detail_commandes", schema = "ecommerce", catalog = "")
public class DetailCommandesEntity {
    private int id;
    private int prixUnitaire;
    private int reduction;
    private int quantite;
    private ProduitEntity produit;
    private CommandesEntity commande;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "reduction")
    public int getReduction() {
        return reduction;
    }

    public void setReduction(int reduction) {
        this.reduction = reduction;
    }

    @Basic
    @Column(name = "quantite")
    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @ManyToOne
    @JoinColumn(name="commande", nullable=false)
    public CommandesEntity getCommande() {
        return commande;
    }

    public void setCommande(CommandesEntity commande) {
        this.commande = commande;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailCommandesEntity that = (DetailCommandesEntity) o;
        return id == that.id && prixUnitaire == that.prixUnitaire && reduction == that.reduction && quantite == that.quantite;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, prixUnitaire, reduction, quantite);
    }

    @OneToOne
    @JoinColumn(name = "produit", referencedColumnName = "id", nullable = false)
    public ProduitEntity getProduit() {
        return produit;
    }

    public void setProduit(ProduitEntity produit) {
        this.produit = produit;
    }
}