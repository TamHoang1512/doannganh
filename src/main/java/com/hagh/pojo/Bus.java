/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.pojo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author 84344
 */
@Entity
@Table(name = "bus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bus.findAll", query = "SELECT b FROM Bus b"),
    @NamedQuery(name = "Bus.findById", query = "SELECT b FROM Bus b WHERE b.id = :id"),
    @NamedQuery(name = "Bus.findByDirection", query = "SELECT b FROM Bus b WHERE b.direction = :direction"),
    @NamedQuery(name = "Bus.findByCapacitySit", query = "SELECT b FROM Bus b WHERE b.capacitySit = :capacitySit"),
    @NamedQuery(name = "Bus.findByStartAt", query = "SELECT b FROM Bus b WHERE b.startAt = :startAt"),
    @NamedQuery(name = "Bus.findByCreatedAt", query = "SELECT b FROM Bus b WHERE b.createdAt = :createdAt"),
    @NamedQuery(name = "Bus.findByImg", query = "SELECT b FROM Bus b WHERE b.img = :img")})
public class Bus implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "direction")
    private short direction;
    
    @Basic(optional = false)
    @NotNull()
    @Column(name = "capacity_sit")
    private int capacitySit;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "busId")
    private Collection<BusTicket> busTicketCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Column(name = "start_at")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date startAt;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Size(max = 255)
    @Column(name = "img")
    private String img;
    @JoinColumn(name = "bus_line_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private BusLine busLineId;
    @Transient
    private MultipartFile imgFile;

    public MultipartFile getImgFile() {
        return imgFile;
    }


    public void setImgFile(MultipartFile imgFile) {
        this.imgFile = imgFile;
    }

    public Bus() {
    }

    public Bus(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCapacitySit() {
        return capacitySit;
    }

    public void setCapacitySit(Integer capacitySit) {
        this.capacitySit = capacitySit;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public BusLine getBusLineId() {
        return busLineId;
    }

    public void setBusLineId(BusLine busLineId) {
        this.busLineId = busLineId;
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
        if (!(object instanceof Bus)) {
            return false;
        }
        Bus other = (Bus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hagh.pojo.Bus[ id=" + id + " ]";
    }

    public short getDirection() {
        return direction;
    }

    public void setDirection(short direction) {
        this.direction = direction;
    }

    

    @XmlTransient
    public Collection<BusTicket> getBusTicketCollection() {
        return busTicketCollection;
    }

    public void setBusTicketCollection(Collection<BusTicket> busTicketCollection) {
        this.busTicketCollection = busTicketCollection;
    }
    
}
