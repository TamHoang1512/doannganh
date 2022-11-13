/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.Nullable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author 84344
 */
@Entity
@Table(name = "station_news")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StationNews.findAll", query = "SELECT s FROM StationNews s"),
    @NamedQuery(name = "StationNews.findById", query = "SELECT s FROM StationNews s WHERE s.id = :id"),
    @NamedQuery(name = "StationNews.findByTitle", query = "SELECT s FROM StationNews s WHERE s.title = :title"),
    @NamedQuery(name = "StationNews.findByCreatedAt", query = "SELECT s FROM StationNews s WHERE s.createdAt = :createdAt"),
    @NamedQuery(name = "StationNews.findByIsBlock", query = "SELECT s FROM StationNews s WHERE s.isBlock = :isBlock")})
public class StationNews implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Size(max = 255, min = 1, message = "Tiêu đề không được để trống.")
    @Column(name = "title")
    private String title;
    
    @Lob
    @Nullable
    @Size(max = 2147483647)
    @Column(name = "content")
    private String content;
    
    @Nullable
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    
    
    @Nullable
    @Column(name = "is_block")
    private Short isBlock;
    
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
    @JsonIgnore
    private Users userId;
    
    @Size(max = 255)
    @Nullable
    @Column(name = "img")
    private String img;
    
    @Transient
    private MultipartFile imgFile;
    
    {
        createdAt = new Date();
        isBlock = 0;
    }
    

    public MultipartFile getImgFile() {
        return imgFile;
    }


    public void setImgFile(MultipartFile imgFile) {
        this.imgFile = imgFile;
    }
    
    public StationNews() {
    }

    public StationNews(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
//        if (!(object instanceof StationNews)) {
//            return false;
//        }
//        StationNews other = (StationNews) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hagh.pojo.StationNews[ id=" + id + " ]";
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
}
