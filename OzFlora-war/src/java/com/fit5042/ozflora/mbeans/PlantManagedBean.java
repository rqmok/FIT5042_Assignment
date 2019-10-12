/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.mbeans;

import com.fit5042.ozflora.auth.entities.User;
import com.fit5042.ozflora.auth.entities.WebsiteUser;
import com.fit5042.ozflora.controllers.LoginController;
import com.fit5042.ozflora.repository.PlantRepository;
import com.fit5042.ozflora.repository.UserRepository;
import com.fit5042.ozflora.repository.entities.Plant;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Zeeshan
 */
@ManagedBean(name = "plantManagedBean", eager = true)
@SessionScoped
public class PlantManagedBean implements Serializable {

    private static final Logger logger = Logger.getLogger(LoginController.class.getName());

    @EJB
    private PlantRepository plantRepository;

    @EJB
    private UserRepository userRepository;

    @ManagedProperty("#{ loginController }")
    private LoginController loginController;

    /**
     * Creates a new instance of PlantManagedBean
     */
    public PlantManagedBean() {
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public List<Plant> getAllPlants() {
        try {
            return this.plantRepository.getAllPlants();
        } catch (Exception e) {
            this.logger.log(Level.SEVERE, null, e);
        }
        return null;
    }

    public void addPlant(Plant plant) {
        try {
            this.plantRepository.addPlant(plant);
        } catch (Exception e) {
            this.logger.log(Level.SEVERE, null, e);
        }
    }

    public void removePlantById(int id) {
        try {
            this.plantRepository.removePlantById(id);
        } catch (Exception e) {
            this.logger.log(Level.SEVERE, null, e);
        }
    }

    public void editPlant(Plant plant) {
        try {
            this.plantRepository.editPlant(plant);
        } catch (Exception e) {
            this.logger.log(Level.SEVERE, null, e);
        }
    }

    public Plant searchPlantById(int id) {
        try {
            return this.plantRepository.searchPlantById(id);
        } catch (Exception e) {
            this.logger.log(Level.SEVERE, null, e);
        }
        return null;
    }

    public void savePlant(Plant plant) {
        try {
            User user = this.loginController.getUser();
            this.userRepository.savePlantToUser(user, plant);
            
            if (user instanceof WebsiteUser) {
                WebsiteUser websiteUser = (WebsiteUser) user;
                if (!websiteUser.getPlants().contains(plant)) {
                    websiteUser.addPlant(plant);
                }
            }
            
            this.loginController.setUser(user);
        } catch (Exception e) {
            logger.log(Level.SEVERE, null, e);
        }
    }

    public void unsavePlant(Plant plant) {
        try {
            User user = this.loginController.getUser();
            this.userRepository.removePlantFromUser(user, plant);
            
            if (user instanceof WebsiteUser) {
                WebsiteUser websiteUser = (WebsiteUser) user;
                if (websiteUser.getPlants().contains(plant)) {
                    websiteUser.removePlant(plant);
                }
            }
            
            this.loginController.setUser(user);
        } catch (Exception e) {
            logger.log(Level.SEVERE, null, e);
        }
    }
    
    public List<Plant> getPlants(String name, String description, String family) {
        try {
            return this.plantRepository.searchPlants(name, description, family);
        } catch (Exception e) {
            logger.log(Level.SEVERE, null, e);
        }
        
        // Exception occured. Return nothing.
        return null;
    }

}
