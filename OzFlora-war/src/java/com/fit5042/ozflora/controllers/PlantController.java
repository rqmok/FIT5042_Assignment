/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.controllers;

import com.fit5042.ozflora.mbeans.PlantManagedBean;
import com.fit5042.ozflora.repository.entities.Plant;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

/**
 *
 * @author Zeeshan
 */
@Named(value = "plantController")
@RequestScoped
public class PlantController {

    @ManagedProperty(value = "#{ plantManagedBean }")
    private final PlantManagedBean plantManagedBean;
    
    private Plant plant;

    /**
     * Creates a new instance of PlantController
     */
    public PlantController() {
        int plantId = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("plantId"));
        
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        this.plantManagedBean = (PlantManagedBean) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "plantManagedBean");
        
        this.plant = this.plantManagedBean.searchPlantById(plantId);
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

}
