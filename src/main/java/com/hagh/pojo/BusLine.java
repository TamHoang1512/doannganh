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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author 84344
 */
@Entity
@Table(name = "bus_line")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BusLine.findAll", query = "SELECT b FROM BusLine b"),
    @NamedQuery(name = "BusLine.findById", query = "SELECT b FROM BusLine b WHERE b.id = :id"),
    @NamedQuery(name = "BusLine.findByDescription", query = "SELECT b FROM BusLine b WHERE b.description = :description"),
    @NamedQuery(name = "BusLine.findByCreatedAt", query = "SELECT b FROM BusLine b WHERE b.createdAt = :createdAt"),
    
    @NamedQuery(name = "BusLine.findByImg", query = "SELECT b FROM BusLine b WHERE b.img = :img"),
    @NamedQuery(name = "BusLine.findByTicketPrice", query = "SELECT b FROM BusLine b WHERE b.ticketPrice = :ticketPrice")})
public class BusLine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    
    @Size(max = 255)
    @Column(name = "img")
    private String img;
    
    @Column(name = "ticket_price")
    private Long ticketPrice;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "busLineId")
    private Collection<Bus> busCollection;
    @JoinColumn(name = "bus_owner_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private BusOwner busOwnerId;
    @JoinColumn(name = "from_pos", referencedColumnName = "id")
    @ManyToOne
    private BusStation fromPos;
    @JoinColumn(name = "to_pos", referencedColumnName = "id")
    @ManyToOne
    private BusStation toPos;
    @Transient
    private MultipartFile imgFile;

    public MultipartFile getImgFile() {
        return imgFile;
    }


    public void setImgFile(MultipartFile imgFile) {
        this.imgFile = imgFile;
    }

    public BusLine() {
    }

    public BusLine(Integer id) {
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

    public Long getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Long ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @XmlTransient
    public Collection<Bus> getBusCollection() {
        return busCollection;
    }

    public void setBusCollection(Collection<Bus> busCollection) {
        this.busCollection = busCollection;
    }

    public BusOwner getBusOwnerId() {
        return busOwnerId;
    }

    public void setBusOwnerId(BusOwner busOwnerId) {
        this.busOwnerId = busOwnerId;
    }

    public BusStation getFromPos() {
        return fromPos;
    }

    public void setFromPos(BusStation fromPos) {
        this.fromPos = fromPos;
    }

    public BusStation getToPos() {
        return toPos;
    }

    public void setToPos(BusStation toPos) {
        this.toPos = toPos;
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
        if (!(object instanceof BusLine)) {
            return false;
        }
        BusLine other = (BusLine) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hagh.pojo.BusLine[ id=" + id + " ]";
    }
    
}
