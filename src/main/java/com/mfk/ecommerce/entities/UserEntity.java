package com.mfk.ecommerce.entities;

import com.mfk.ecommerce.enumerateur.DeviseEnum;

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
    private String actif;
    private DeviseEnum devise;
    private String password;
    private List<AdresseEntity> adresses;

    public UserEntity() {
    }

    public UserEntity(int i, String username, String name, String surname, String email, String role, String telephone, String actif, String password, AdresseEntity a) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
        this.telephone = telephone;
        this.actif = actif;
        this.password = password;
        this.adresses = adresses;
    }

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
    public String getActif() {
        return actif;
    }

    public void setActif(String actif) {
        this.actif = actif;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "devise")
    public DeviseEnum getDevise() {
        return devise;
    }

    public void setDevise(DeviseEnum devise) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(email, that.email) && Objects.equals(role, that.role) && Objects.equals(telephone, that.telephone) && Objects.equals(actif, that.actif) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, name, surname, email, role, telephone, actif, password);
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    public List<AdresseEntity> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<AdresseEntity> adresses) {
        this.adresses = adresses;
    }
}