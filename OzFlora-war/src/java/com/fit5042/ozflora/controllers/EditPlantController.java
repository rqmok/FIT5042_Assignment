/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.controllers;

import com.fit5042.ozflora.mbeans.PlantManagedBean;
import com.fit5042.ozflora.repository.entities.Plant;
import java.io.Serializable;
import javax.el.ELContext;
import javax.inject.Named;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author zeeshan
 */
@Named(value = "editPlantController")
@ViewScoped
public class EditPlantController implements Serializable {
    
    @ManagedProperty(value = "#{ plantManagedBean }")
    private final PlantManagedBean plantManagedBean;
    
    private Plant plant;

    /**
     * Creates a new instance of EditPlantController
     */
    public EditPlantController() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        this.plantManagedBean = (PlantManagedBean) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "plantManagedBean");
        
        int plantId = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("plantId"));
        this.plant = this.plantManagedBean.searchPlantById(plantId);
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }
    
    private String getPlantDetailsRedirect() {
        return PageUrl.getPageRedirect(PageUrl.PLANT_DETAILS) + "&plantId=" + plant.getId();
    }
    
    public String savePlant() {
        this.plantManagedBean.updatePlant(plant);
        return this.getPlantDetailsRedirect();
    }
    
    public String cancelEdit() {
        return this.getPlantDetailsRedirect();
    }
    
}
