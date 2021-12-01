package com.mfk.ecommerce.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "adresse", schema = "ecommerce", catalog = "")
public class AdresseEntity {
    private int id;
    private String ville;
    private String codePostal;
    private String pays;
    private UserEntity user;

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
    @Column(name = "ville")
    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Basic
    @Column(name = "code_postal")
    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    @Basic
    @Column(name = "pays")
    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @ManyToOne
    @JoinColumn(name="user", nullable=false)
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdresseEntity that = (AdresseEntity) o;
        return id == that.id && Objects.equals(ville, that.ville) && Objects.equals(codePostal, that.codePostal) && Objects.equals(pays, that.pays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ville, codePostal, pays);
    }
}