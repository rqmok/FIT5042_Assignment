/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.controllers;

import com.fit5042.ozflora.mbeans.PlantManagedBean;
import com.fit5042.ozflora.repository.entities.Plant;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

/**
 *
 * @author Zeeshan
 */
@Named(value = "plantsController")
@RequestScoped
public class PlantsController implements Serializable {
    
    @ManagedProperty(value = "#{ plantManagedBean }")
    private PlantManagedBean plantManagedBean;
    
    private List<Plant> plants;

    /**
     * Creates a new instance of PlantsController
     */
    public PlantsController() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        this.plantManagedBean = (PlantManagedBean) FacesContext.getCurrentInstance()
                .getApplication().getELResolver().getValue(elContext, null, "plantManagedBean");
        
        this.plants = new ArrayList<>();
        
        this.updatePlants();
    }
    
    private void updatePlants() {
        this.plants.clear();

        this.plantManagedBean.getAllPlants().forEach(plant -> {
            this.plants.add(plant);
            System.out.println(plant.getName());
        });
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }

    public PlantManagedBean getPlantManagedBean() {
        return plantManagedBean;
    }

    public void setPlantManagedBean(PlantManagedBean plantManagedBean) {
        this.plantManagedBean = plantManagedBean;
    }
    
}
