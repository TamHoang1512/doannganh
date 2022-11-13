/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.pojo;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author 84344
 */
@Entity
@Table(name = "bus_owner")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BusOwner.findAll", query = "SELECT b FROM BusOwner b"),
    @NamedQuery(name = "BusOwner.findById", query = "SELECT b FROM BusOwner b WHERE b.id = :id"),
    @NamedQuery(name = "BusOwner.findByName", query = "SELECT b FROM BusOwner b WHERE b.name = :name"),
    @NamedQuery(name = "BusOwner.findByPhone", query = "SELECT b FROM BusOwner b WHERE b.phone = :phone"),
    @NamedQuery(name = "BusOwner.findByDescription", query = "SELECT b FROM BusOwner b WHERE b.description = :description"),
    @NamedQuery(name = "BusOwner.findByImg", query = "SELECT b FROM BusOwner b WHERE b.img = :img"),
    @NamedQuery(name = "BusOwner.findByIsActive", query = "SELECT b FROM BusOwner b WHERE b.isActive = :isActive"),
    @NamedQuery(name = "BusOwner.findByIsBlock", query = "SELECT b FROM BusOwner b WHERE b.isBlock = :isBlock"),
    @NamedQuery(name = "BusOwner.findByIsFreight", query = "SELECT b FROM BusOwner b WHERE b.isFreight = :isFreight")})
public class BusOwner implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "is_freight")
    private short isFreight;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "busOwnerId")
    private Collection<Rating> ratingCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "busOwnerId")
    private Collection<StaffBus> staffBusCollection;

    @OneToMany(mappedBy = "ownerId")
    private Collection<Freight> freightCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255,min = 1,message = "Tên không được để trống")
    @Column(name = "name")
    private String name;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 10,min = 10,message = "Số điện thoại phải đủ 10 số")
    @Column(name = "phone")
    private String phone;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    @Size(max = 255)
    @Column(name = "img")
    private String img;
    @Column(name = "is_active")
    private Short isActive;
    @Column(name = "is_block")
    private Short isBlock;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "busOwnerId")
    private Collection<BusLine> busLineCollection;
    @JoinColumn(name = "owner_user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users ownerUserId;
    
    @Transient
    private MultipartFile imgFile;

    public MultipartFile getImgFile() {
        return imgFile;
    }


    public void setImgFile(MultipartFile imgFile) {
        this.imgFile = imgFile;
    }
    
    {
        isActive=0;
        isBlock=0;
        isFreight=0;
    }

    public BusOwner() {
    }

    public BusOwner(Integer id) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Short getIsActive() {
        return isActive;
    }

    public void setIsActive(Short isActive) {
        this.isActive = isActive;
    }

    public Short getIsBlock() {
        return isBlock;
    }

    public void setIsBlock(Short isBlock) {
        this.isBlock = isBlock;
    }

    public Short getIsFreight() {
        return isFreight;
    }

    public void setIsFreight(Short isFreight) {
        this.isFreight = isFreight;
    }

    @XmlTransient
    public Collection<BusLine> getBusLineCollection() {
        return busLineCollection;
    }

    public void setBusLineCollection(Collection<BusLine> busLineCollection) {
        this.busLineCollection = busLineCollection;
    }

    public Users getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(Users ownerUserId) {
        this.ownerUserId = ownerUserId;
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
        if (!(object instanceof BusOwner)) {
            return false;
        }
        BusOwner other = (BusOwner) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hagh.pojo.BusOwner[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<Freight> getFreightCollection() {
        return freightCollection;
    }

    public void setFreightCollection(Collection<Freight> freightCollection) {
        this.freightCollection = freightCollection;
    }


    @XmlTransient
    public Collection<Rating> getRatingCollection() {
        return ratingCollection;
    }

    public void setRatingCollection(Collection<Rating> ratingCollection) {
        this.ratingCollection = ratingCollection;
    }

    @XmlTransient
    public Collection<StaffBus> getStaffBusCollection() {
        return staffBusCollection;
    }

    public void setStaffBusCollection(Collection<StaffBus> staffBusCollection) {
        this.staffBusCollection = staffBusCollection;
    }
    
}
