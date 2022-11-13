/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 84344
 */
@Entity
@Table(name = "bus_ticket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BusTicket.findAll", query = "SELECT b FROM BusTicket b"),
    @NamedQuery(name = "BusTicket.findById", query = "SELECT b FROM BusTicket b WHERE b.id = :id"),
    @NamedQuery(name = "BusTicket.findByNumberOfSit", query = "SELECT b FROM BusTicket b WHERE b.numberOfSit = :numberOfSit"),
    @NamedQuery(name = "BusTicket.findByIsPurchased", query = "SELECT b FROM BusTicket b WHERE b.isPurchased = :isPurchased")})
public class BusTicket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "number_of_sit")
    private Integer numberOfSit;
    
    @Column(name = "is_purchased")
    private Short isPurchased;
    
    @JoinColumn(name = "bus_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Bus busId;
    
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users userId;

    public BusTicket() {
    }

    public BusTicket(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumberOfSit() {
        return numberOfSit;
    }

    public void setNumberOfSit(Integer numberOfSit) {
        this.numberOfSit = numberOfSit;
    }

    public Short getIsPurchased() {
        return isPurchased;
    }

    public void setIsPurchased(Short isPurchased) {
        this.isPurchased = isPurchased;
    }

    public Bus getBusId() {
        return busId;
    }

    public void setBusId(Bus busId) {
        this.busId = busId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BusTicket)) {
            return false;
        }
        BusTicket other = (BusTicket) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hagh.pojo.BusTicket[ id=" + id + " ]";
    }
    
}
