/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.mbeans;

import com.fit5042.ozflora.repository.PlantRepository;
import com.fit5042.ozflora.repository.entities.Plant;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.jboss.logging.Logger;

/**
 *
 * @author Zeeshan
 */
@ManagedBean(name = "plantManagedBean", eager = true)
@SessionScoped
public class PlantManagedBean implements Serializable {
    
    private final Logger logger;
    
    @EJB
    private PlantRepository plantRepository;

    /**
     * Creates a new instance of PlantManagedBean
     */
    public PlantManagedBean() {
        this.logger = Logger.getLogger(this.getClass().getName());
    }
    
    public List<Plant> getAllPlants() {
        try {
            return this.plantRepository.getAllPlants();
        } catch (Exception e) {
            this.logger.log(Logger.Level.FATAL, e);
        }
        return null;
    }
    
    public void addPlant(Plant plant) {
        try {
            this.plantRepository.addPlant(plant);
        } catch (Exception e) {
            this.logger.log(Logger.Level.FATAL, e);
        }
    }
    
    public void removePlantById(int id) {
        try {
            this.plantRepository.removePlantById(id);
        } catch (Exception e) {
            this.logger.log(Logger.Level.FATAL, e);
        }
    }
    
    public void editPlant(Plant plant) {
        try {
            this.plantRepository.editPlant(plant);
        } catch (Exception e) {
            this.logger.log(Logger.Level.FATAL, e);
        }
    }
    
    public Plant searchPlantById(int id) {
        try {
            return this.plantRepository.searchPlantById(id);
        } catch (Exception e) {
            this.logger.log(Logger.Level.FATAL, e);
        }
        return null;
    }
    
}
