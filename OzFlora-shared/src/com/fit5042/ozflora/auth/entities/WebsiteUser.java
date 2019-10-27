/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.auth.entities;

import com.fit5042.ozflora.repository.entities.Plant;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * A regular web site user that has the ability to save plants.
 * 
 * @author Zeeshan Khan
 */
@Entity
@DiscriminatorValue(value = "U")
@PrimaryKeyJoinColumn(name = "email")
public class WebsiteUser extends User implements Serializable {
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "websiteUsers")
    private Set<Plant> plants;

    public WebsiteUser() {
        super();
    }

    public WebsiteUser(String email, String name, String password) {
        super(email, name, password);
        this.plants = new HashSet<>();
    }

    public Set<Plant> getPlants() {
        return plants;
    }

    public void setPlants(Set<Plant> plants) {
        this.plants = plants;
    }
    
    public void addPlant(Plant plant) {
        this.plants.add(plant);
    }
    
    public void removePlant(Plant plant) {
        this.plants.remove(plant);
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
        if (!(object instanceof WebsiteUser)) {
            return false;
        }
        WebsiteUser other = (WebsiteUser) object;
        if ((this.getEmail() == null && other.getEmail() != null) || (this.getEmail() != null && !this.getEmail().equals(other.getEmail()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fit5042.ozflora.auth.entities.WebsiteUser[ id=" + getEmail() + " ]";
    }
    
}
