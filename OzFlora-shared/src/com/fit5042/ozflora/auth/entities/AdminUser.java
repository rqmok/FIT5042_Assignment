/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.auth.entities;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author zeeshan
 */
@Entity
@DiscriminatorValue(value = "A")
@PrimaryKeyJoinColumn(name = "email")
public class AdminUser extends User implements Serializable {

    public AdminUser() {
        super();
    }

    public AdminUser(String email, String name, String password) {
        super(email, name, password);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getEmail() != null ? this.getEmail().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdminUser)) {
            return false;
        }
        AdminUser other = (AdminUser) object;
        if ((this.getEmail() == null && other.getEmail() != null) || (this.getEmail() != null && !this.getEmail().equals(other.getEmail()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fit5042.ozflora.auth.entities.AdminUser[ id=" + this.getEmail() + " ]";
    }
    
}
