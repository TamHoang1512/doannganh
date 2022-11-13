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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author 84344
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUserId", query = "SELECT u FROM Users u WHERE u.userId = :userId"),
    @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByAvatar", query = "SELECT u FROM Users u WHERE u.avatar = :avatar"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByPhone", query = "SELECT u FROM Users u WHERE u.phone = :phone")})
public class Users implements Serializable {




    public MultipartFile getAvatarFile() {
        return avatarFile;
    }

    public void setAvatarFile(MultipartFile avatarFile) {
        this.avatarFile = avatarFile;
    }

    @OneToMany(mappedBy = "userId")
    private Collection<StationNews> stationNewsCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    
    @Basic(optional = false)
    @Size(min = 4, max = 24,message = "{message.validate.user.username}")
    @Column(name = "username")
    private String username;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 8, max = 255, message = "{message.validate.user.password.size}")
    @Column(name = "password")
    private String password;
    
    @Size(max = 255)
    @Column(name = "avatar")
    private String avatar;
    
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Email không hợp lệ")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "email")
    private String email;
//     @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 25,min = 10,message = "Số điện thoại phải đủ 10 số")
    @Column(name = "phone")
    private String phone;
    
    @OneToMany(mappedBy = "adminId")
    private Collection<BusStation> busStationCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<BusTicket> busTicketCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Rating> ratingCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<StaffBus> staffBusCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ownerUserId")
    private Collection<BusOwner> busOwnerCollection;
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    @ManyToOne(optional = false)
    private Role roleId;
    
    @Transient
    private MultipartFile avatarFile;
    

    
    @Transient
    private String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Users() {
    }

    public Users(Integer userId) {
        this.userId = userId;
    }

    public Users(Integer userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @XmlTransient
    public Collection<BusStation> getBusStationCollection() {
        return busStationCollection;
    }

    public void setBusStationCollection(Collection<BusStation> busStationCollection) {
        this.busStationCollection = busStationCollection;
    }


    @XmlTransient
    public Collection<BusTicket> getBusTicketCollection() {
        return busTicketCollection;
    }

    public void setBusTicketCollection(Collection<BusTicket> busTicketCollection) {
        this.busTicketCollection = busTicketCollection;
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

    @XmlTransient
    public Collection<BusOwner> getBusOwnerCollection() {
        return busOwnerCollection;
    }

    public void setBusOwnerCollection(Collection<BusOwner> busOwnerCollection) {
        this.busOwnerCollection = busOwnerCollection;
    }

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hagh.pojo.Users[ userId=" + userId + " ]";
    }

    @XmlTransient
    public Collection<StationNews> getStationNewsCollection() {
        return stationNewsCollection;
    }

    public void setStationNewsCollection(Collection<StationNews> stationNewsCollection) {
        this.stationNewsCollection = stationNewsCollection;
    }
    
}
