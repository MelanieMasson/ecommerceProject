package com.mfk.ecommerce.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "commandes", schema = "ecommerce", catalog = "")
public class CommandesEntity {
    private int id;
    private Date dateCmd;
    private Date dateValidation;
    private Date dateExpedition;
    private Date dateReceived;
    private int valueGlobal;
    private int reduction;
    private int price;
    private UserEntity user;
    private List<DetailCommandesEntity> details_commandes;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date_cmd")
    public Date getDateCmd() {
        return dateCmd;
    }

    public void setDateCmd(Date dateCmd) {
        this.dateCmd = dateCmd;
    }

    @Basic
    @Column(name = "date_validation")
    public Date getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(Date dateValidation) {
        this.dateValidation = dateValidation;
    }

    @Basic
    @Column(name = "date_expedition")
    public Date getDateExpedition() {
        return dateExpedition;
    }

    public void setDateExpedition(Date dateExpedition) {
        this.dateExpedition = dateExpedition;
    }

    @Basic
    @Column(name = "date_received")
    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    @Basic
    @Column(name = "value_global")
    public int getValueGlobal() {
        return valueGlobal;
    }

    public void setValueGlobal(int valueGlobal) {
        this.valueGlobal = valueGlobal;
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
    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandesEntity that = (CommandesEntity) o;
        return id == that.id && valueGlobal == that.valueGlobal && reduction == that.reduction && price == that.price && Objects.equals(dateCmd, that.dateCmd) && Objects.equals(dateValidation, that.dateValidation) && Objects.equals(dateExpedition, that.dateExpedition) && Objects.equals(dateReceived, that.dateReceived);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateCmd, dateValidation, dateExpedition, dateReceived, valueGlobal, reduction, price);
    }

    @OneToOne
    @JoinColumn(name = "user", referencedColumnName = "id", nullable = false)
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "commande")
    public List<DetailCommandesEntity> getDetails_commandes() {
        return details_commandes;
    }

    public void setDetails_commandes(List<DetailCommandesEntity> details_commandes) {
        this.details_commandes = details_commandes;
    }
}
