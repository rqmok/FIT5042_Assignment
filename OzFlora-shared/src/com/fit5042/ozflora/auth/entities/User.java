/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.auth.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * A basic user.
 * 
 * @author Zeeshan Khan
 */
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "USER_TYPE",
        discriminatorType = DiscriminatorType.STRING,
        length = 1)
@NamedQueries({
    @NamedQuery(name = User.GET_ALL_QUERY_NAME, query = "SELECT u FROM User u ORDER BY u.email")})
public abstract class User implements Serializable {
    
    public static final String GET_ALL_QUERY_NAME = "User.getAll";

    @Id
    @Column(name = "email", nullable = false, length = 255)
    @NotNull
    @Pattern(regexp = "^(.+)@(.+)(\\.)(.+)$", message = "Invalid email address")
    private String email;

    @Column(name = "name", nullable = false, length = 30)
    @NotNull
    private String name;

    @Column(name = "password", nullable = false, length = 64)
    @NotNull
    private String password;
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @NotNull
    private UserGroup userGroup;

    public User() {
    }

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fit5042.ozflora.auth.entities.User[ id=" + email + " ]";
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

}
