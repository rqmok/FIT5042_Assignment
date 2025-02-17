/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.auth.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

/**
 * Represents a worker allowed to manage web site content.
 * 
 * @author Zeeshan Khan
 */
@Entity
@DiscriminatorValue(value = "W")
@PrimaryKeyJoinColumn(name = "email")
public class WorkerUser extends User implements Serializable {
    
    @Column(name = "mobile", nullable = false, length = 10)
    @Size(min = 10, max = 10, message = "Mobile number has to be 10 digits")
    @NotNull
    private String mobile;
    
    @Temporal(TemporalType.DATE)
    @Past(message = "Date of birth can only be in the past")
    @NotNull
    private Date dob;
    
    @Embedded
    @NotNull
    private Address address;
    
    public WorkerUser() {
        super();
    }

    public WorkerUser(String mobile, Date dob, Address address, String email, String name, String password) {
        super(email, name, password);
        this.mobile = mobile;
        this.dob = dob;
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getEmail() != null ? getEmail().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WorkerUser)) {
            return false;
        }
        WorkerUser other = (WorkerUser) object;
        if ((this.getEmail() == null && other.getEmail() != null) || (this.getEmail() != null && !this.getEmail().equals(other.getEmail()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fit5042.ozflora.auth.entities.WorkerUser[ id=" + getEmail() + " ]";
    }
    
}
