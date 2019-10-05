/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.controllers;

import com.fit5042.ozflora.mbeans.PlantManagedBean;
import com.fit5042.ozflora.repository.UserRepository;
import com.fit5042.ozflora.repository.entities.Plant;
import javax.ejb.EJB;
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

    @EJB
    private UserRepository userRepository;

    private Plant plant;

    /**
     * Creates a new instance of PlantController
     */
    public PlantController() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        this.plantManagedBean = (PlantManagedBean) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "plantManagedBean");

        int plantId = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("plantId"));
        this.plant = this.plantManagedBean.searchPlantById(plantId);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public String savePlant() {
        plantManagedBean.savePlant(plant);
        return "detail?plantId=" + plant.getId() + "&faces-redirect=true";
    }

    public String unsavePlant() {
        plantManagedBean.unsavePlant(plant);
        return "detail?plantId=" + plant.getId() + "&faces-redirect=true";
    }

}
