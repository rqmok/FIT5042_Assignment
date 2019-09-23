/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.repository;

import com.fit5042.ozflora.repository.entities.Plant;
import java.util.List;
import javax.ejb.Remote;

/**
 * A persistence interface of {@link Plant}.
 * @author Zeeshan
 */
@Remote
public interface PlantRepository {
    /**
     * Get a {@link List} of all the {@link Plant} objects in the repository.
     * 
     * @return The {@link List} of all {@link Plant Plants}.
     */
    public List<Plant> getAllPlants();
    
    /**
     * Add a given {@link Plant} to the repository.
     * 
     * @param plant The {@link Plant} to add.
     * @throws Exception 
     */
    public void addPlant(Plant plant) throws Exception;
    
    /**
     * Removes a {@link Plant} from the repository with the given ID.
     * 
     * @param id The ID of the {@link Plant} to remove.
     * @throws Exception 
     */
    public void removePlantById(int id) throws Exception;
    
    /**
     * Update a given {@link Plant} in the repository.
     * 
     * @param plant The {@link Plant} to update.
     * @throws Exception 
     */
    public void editPlant(Plant plant) throws Exception;
    
    /**
     * Find a {@link Plant} by its ID.
     * 
     * @param id The ID of the {@link Plant} to find.
     * @return The found {@link Plant}.
     * @throws Exception 
     */
    public Plant searchPlantById(int id) throws Exception;
}
