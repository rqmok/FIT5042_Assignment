/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.controllers;

import com.fit5042.ozflora.mbeans.PlantManagedBean;
import com.fit5042.ozflora.repository.entities.Plant;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.el.ELContext;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

/**
 *
 * @author Zeeshan
 */
@Named(value = "homeController")
@SessionScoped
public class HomeController implements Serializable {

    @ManagedProperty(value = "#{ plantManagedBean }")
    private PlantManagedBean plantManagedBean;

    private List<Plant> featuredPlants;

    /**
     * Creates a new instance of HomeController
     */
    public HomeController() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        this.plantManagedBean = (PlantManagedBean) FacesContext.getCurrentInstance()
                .getApplication().getELResolver().getValue(elContext, null, "plantManagedBean");

        this.featuredPlants = new ArrayList<>();

        this.updateFeaturedPlants();
    }

    public List<Plant> getFeaturedPlants() {
        return featuredPlants;
    }

    public void setFeaturedPlants(List<Plant> featuredPlants) {
        this.featuredPlants = featuredPlants;
    }

    private void updateFeaturedPlants() {
        this.featuredPlants.clear();
        List<Plant> allPlants = this.plantManagedBean.getAllPlants();

        Random rand = new Random();
        int numPlants = 6;
        for (int i = 0; i < numPlants; i++) {
            int randomIndex = rand.nextInt(allPlants.size());
            this.featuredPlants.add(allPlants.get(randomIndex));
        }
    }

    public PlantManagedBean getPlantManagedBean() {
        return plantManagedBean;
    }

    public void setPlantManagedBean(PlantManagedBean plantManagedBean) {
        this.plantManagedBean = plantManagedBean;
    }

}
