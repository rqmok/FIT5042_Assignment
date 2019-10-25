/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.auth.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Zeeshan
 */
@Entity
@Table(name = "user_groups")
public class UserGroup implements Serializable {
    
    public final static String USERS_GROUP = "users";
    public final static String WORKERS_GROUP = "workers";
    public final static String ADMIN_GROUP = "admin";

    @Id
    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "groupname", nullable = false, length = 32)
    private String groupName;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "email", referencedColumnName = "email")
    private User user;

    public UserGroup() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the email fields are not set
        if (!(object instanceof UserGroup)) {
            return false;
        }
        UserGroup other = (UserGroup) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fit5042.ozflora.auth.entities.Group[ id=" + email + " ]";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
