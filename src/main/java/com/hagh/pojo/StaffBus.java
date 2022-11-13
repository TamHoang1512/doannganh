/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 84344
 */
@Entity
@Table(name = "staff_bus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StaffBus.findAll", query = "SELECT s FROM StaffBus s"),
    @NamedQuery(name = "StaffBus.findById", query = "SELECT s FROM StaffBus s WHERE s.id = :id")})
public class StaffBus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "bus_owner_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private BusOwner busOwnerId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users userId;

    public StaffBus() {
    }

    public StaffBus(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BusOwner getBusOwnerId() {
        return busOwnerId;
    }

    public void setBusOwnerId(BusOwner busOwnerId) {
        this.busOwnerId = busOwnerId;
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
        if (!(object instanceof StaffBus)) {
            return false;
        }
        StaffBus other = (StaffBus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hagh.pojo.StaffBus[ id=" + id + " ]";
    }
    
}
