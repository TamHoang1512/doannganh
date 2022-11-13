/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.pojo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
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
@Table(name = "bus_station")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BusStation.findAll", query = "SELECT b FROM BusStation b"),
    @NamedQuery(name = "BusStation.findById", query = "SELECT b FROM BusStation b WHERE b.id = :id"),
    @NamedQuery(name = "BusStation.findByName", query = "SELECT b FROM BusStation b WHERE b.name = :name"),
    @NamedQuery(name = "BusStation.findByLocation", query = "SELECT b FROM BusStation b WHERE b.location = :location"),
    @NamedQuery(name = "BusStation.findByImg", query = "SELECT b FROM BusStation b WHERE b.img = :img"),
    @NamedQuery(name = "BusStation.findByCreatedAt", query = "SELECT b FROM BusStation b WHERE b.createdAt = :createdAt"),
    @NamedQuery(name = "BusStation.findByIsBlock", query = "SELECT b FROM BusStation b WHERE b.isBlock = :isBlock")})
public class BusStation implements Serializable {



    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255,min = 1,message = "Tên không được để trống")
    @Column(name = "name")
    private String name;
    @Size(max = 255,min = 1,message = "Địa chỉ không được để trống")
    @Column(name = "location")
    private String location;
    @Size(max = 255)
    @Column(name = "img")
    private String img;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "is_block")
    private Short isBlock;
    @JoinColumn(name = "admin_id", referencedColumnName = "user_id")
    @ManyToOne
    private Users adminId;
    @OneToMany(mappedBy = "fromPos")
    private Collection<BusLine> busLineCollection;
    @OneToMany(mappedBy = "toPos")
    private Collection<BusLine> busLineCollection1;
    
    {
        isBlock = 0;
        createdAt = new Date();
    }
    
    @Transient
    private MultipartFile imgFile;

    public BusStation() {
    }

    public BusStation(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    public Short getIsBlock() {
        return isBlock;
    }

    public void setIsBlock(Short isBlock) {
        this.isBlock = isBlock;
    }

    public Users getAdminId() {
        return adminId;
    }

    public void setAdminId(Users adminId) {
        this.adminId = adminId;
    }

    @XmlTransient
    public Collection<BusLine> getBusLineCollection() {
        return busLineCollection;
    }

    public void setBusLineCollection(Collection<BusLine> busLineCollection) {
        this.busLineCollection = busLineCollection;
    }

    @XmlTransient
    public Collection<BusLine> getBusLineCollection1() {
        return busLineCollection1;
    }

    public void setBusLineCollection1(Collection<BusLine> busLineCollection1) {
        this.busLineCollection1 = busLineCollection1;
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
        if (!(object instanceof BusStation)) {
            return false;
        }
        BusStation other = (BusStation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hagh.pojo.BusStation[ id=" + id + " ]";
    }

    /**
     * @return the imgFile
     */
    public MultipartFile getImgFile() {
        return imgFile;
    }

    /**
     * @param imgFile the imgFile to set
     */
    public void setImgFile(MultipartFile imgFile) {
        this.imgFile = imgFile;
    }

    
}
