/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.repository.entities;

import com.fit5042.ozflora.auth.entities.WebsiteUser;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

/**
 * Represents a Plant.
 *
 * @author Zeeshan
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Plant.GET_ALL_QUERY_NAME, query = "SELECT p FROM Plant p ORDER BY p.id")})
public class Plant implements Serializable {

    public static final String GET_ALL_QUERY_NAME = "Plant.getAll";

    @Id
    @Column(name = "plant_id")
    private int id;
    
    @NotNull
    private String name;
    
    @NotNull
    private String description;
    
    @Column(name = "image_url")
    private String imageUrl;
    
    @NotNull
    private String family;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<WebsiteUser> websiteUsers;

    public Plant() {
    }

    public Plant(int id, String name, String description, String imageUrl, String family) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.family = family;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public Set<WebsiteUser> getWebsiteUsers() {
        return websiteUsers;
    }

    public void setWebsiteUsers(Set<WebsiteUser> websiteUsers) {
        this.websiteUsers = websiteUsers;
    }
    
    public void addWebsiteUser(WebsiteUser user) {
        this.websiteUsers.add(user);
    }
    
    public void removeWebsiteUser(WebsiteUser user) {
        this.websiteUsers.remove(user);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plant)) {
            return false;
        }
        Plant other = (Plant) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fit5042.ozflora.Plant[ id=" + id + " ]";
    }

}
