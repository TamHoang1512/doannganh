/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hagh.pojo;

import java.io.Serializable;
import java.util.Date;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author 84344
 */
@Entity
@Table(name = "freight")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Freight.findAll", query = "SELECT f FROM Freight f"),
    @NamedQuery(name = "Freight.findById", query = "SELECT f FROM Freight f WHERE f.id = :id"),
    @NamedQuery(name = "Freight.findBySendName", query = "SELECT f FROM Freight f WHERE f.sendName = :sendName"),
    @NamedQuery(name = "Freight.findBySendPhone", query = "SELECT f FROM Freight f WHERE f.sendPhone = :sendPhone"),
    @NamedQuery(name = "Freight.findBySendEmail", query = "SELECT f FROM Freight f WHERE f.sendEmail = :sendEmail"),
    @NamedQuery(name = "Freight.findBySendDate", query = "SELECT f FROM Freight f WHERE f.sendDate = :sendDate"),
    @NamedQuery(name = "Freight.findByReceiveName", query = "SELECT f FROM Freight f WHERE f.receiveName = :receiveName"),
    @NamedQuery(name = "Freight.findByReceiveEmail", query = "SELECT f FROM Freight f WHERE f.receiveEmail = :receiveEmail"),
    @NamedQuery(name = "Freight.findByReceivePhone", query = "SELECT f FROM Freight f WHERE f.receivePhone = :receivePhone"),
    @NamedQuery(name = "Freight.findByReceiveDate", query = "SELECT f FROM Freight f WHERE f.receiveDate = :receiveDate"),
    @NamedQuery(name = "Freight.findByTotalPrice", query = "SELECT f FROM Freight f WHERE f.totalPrice = :totalPrice"),
    @NamedQuery(name = "Freight.findByComplete", query = "SELECT f FROM Freight f WHERE f.complete = :complete")})
public class Freight implements Serializable {

    @Basic(optional = false)
    @NotNull(message = "Không được để trống")
    @Column(name = "total_price")
    private long totalPrice;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255, min = 1,message = "Tên người gửi không được trống")
    @Column(name = "send_name")
    private String sendName;
    @Size(max = 255, min = 1,message = "SDT người gửi không được trống")
    @Column(name = "send_phone")
    private String sendPhone;
    @Size(max = 255, min = 1,message = "Email người gửi không được trống")
    @Column(name = "send_email")
    private String sendEmail;
    @Column(name = "send_date")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date sendDate;
    @Size(max = 255, min = 1,message = "Tên người nhận không được trống")
    @Column(name = "receive_name")
    private String receiveName;
    @Size(max = 255, min = 1,message = "Email người nhận không được trống")
    @Column(name = "receive_email")
    private String receiveEmail;
    @Size(max = 255, min = 1,message = "SDT người nhận không được trống")
    @Column(name = "receive_phone")
    private String receivePhone;
    @Column(name = "receive_date")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date receiveDate;
    @Lob
    @Size(max = 65535)
    @Column(name = "info")
    private String info;
    @Column(name = "complete")
    private Short complete;
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @ManyToOne
    private BusOwner ownerId;
    {
        complete = 0;
    }

    public Freight() {
    }

    public Freight(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getSendPhone() {
        return sendPhone;
    }

    public void setSendPhone(String sendPhone) {
        this.sendPhone = sendPhone;
    }

    public String getSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(String sendEmail) {
        this.sendEmail = sendEmail;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceiveEmail() {
        return receiveEmail;
    }

    public void setReceiveEmail(String receiveEmail) {
        this.receiveEmail = receiveEmail;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Short getComplete() {
        return complete;
    }

    public void setComplete(Short complete) {
        this.complete = complete;
    }

    public BusOwner getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(BusOwner ownerId) {
        this.ownerId = ownerId;
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
        if (!(object instanceof Freight)) {
            return false;
        }
        Freight other = (Freight) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hagh.pojo.Freight[ id=" + id + " ]";
    }

    
}
