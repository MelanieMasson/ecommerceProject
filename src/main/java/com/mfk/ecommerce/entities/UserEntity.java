package com.mfk.ecommerce.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "ecommerce", catalog = "")
public class UserEntity {
    private int id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String role;
    private String telephone;
    private byte actif;
    private Enum devise;
    private String password;
    private List<AdresseEntity> adresses;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "telephone")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "actif")
    public byte getActif() {
        return actif;
    }

    public void setActif(byte actif) {
        this.actif = actif;
    }

    @Basic
    @Column(name = "devise")
    public Enum getDevise() {
        return devise;
    }

    public void setDevise(Enum devise) {
        this.devise = devise;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
/*
    @Override
    public boolean equals(Enum o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id && actif == that.actif && Objects.equals(username, that.username) && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(email, that.email) && Objects.equals(role, that.role) && Objects.equals(telephone, that.telephone) && Objects.equals(devise, that.devise) && Objects.equals(password, that.password);
    }

 */

    @Override
    public int hashCode() {
        return Objects.hash(id, username, name, surname, email, role, telephone, actif, devise, password);
    }

    @OneToMany(mappedBy = "user")
    public List<AdresseEntity> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<AdresseEntity> adresses) {
        this.adresses = adresses;
    }
}
