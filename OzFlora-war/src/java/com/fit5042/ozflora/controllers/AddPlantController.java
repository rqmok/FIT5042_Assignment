/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.controllers;

import com.fit5042.ozflora.mbeans.PlantManagedBean;
import com.fit5042.ozflora.repository.entities.Plant;
import javax.el.ELContext;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

/**
 *
 * @author zeeshan
 */
@Named(value = "addPlantController")
@RequestScoped
public class AddPlantController {
    
    @ManagedProperty(value = "#{ plantManagedBean }")
    private final PlantManagedBean plantManagedBean;
    
    private String name;
    private String family;
    private String imageUrl;
    private String description;

    /**
     * Creates a new instance of AddPlantController
     */
    public AddPlantController() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        this.plantManagedBean = (PlantManagedBean) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "plantManagedBean");
    }

    public PlantManagedBean getPlantManagedBean() {
        return plantManagedBean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    private String getRedirectUrl() {
        return PageUrl.getPageRedirect(PageUrl.INDEX);
    }
    
    public String addPlant() {
        Plant plant = new Plant();
        plant.setName(name);
        plant.setFamily(family);
        plant.setImageUrl(imageUrl);
        plant.setDescription(description);
        
        this.plantManagedBean.addPlant(plant);
        
        return this.getRedirectUrl();
    }
    
    public String cancel() {
        return this.getRedirectUrl();
    }
    
}
