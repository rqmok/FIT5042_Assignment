/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.repository;

import com.fit5042.ozflora.repository.entities.Plant;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * JPA Implementation of the PlantRepository interface.
 *
 * @author Zeeshan
 */
@Stateless
@LocalBean
public class PlantRepositoryImpl implements PlantRepository {

    @PersistenceContext(unitName = "OzFlora-ejbPU")
    private EntityManager entityManager;

    @Override
    public List<Plant> getAllPlants() {
        return this.entityManager.createNamedQuery(Plant.GET_ALL_QUERY_NAME).getResultList();
    }

    @Override
    public void addPlant(Plant plant) throws Exception {
        List<Plant> plants = this.entityManager.createNamedQuery(Plant.GET_ALL_QUERY_NAME).getResultList();
        plant.setId(plants.get(0).getId() + 1);
        entityManager.persist(plant);
    }

    @Override
    public void removePlantById(int id) throws Exception {
        Plant plant = this.searchPlantById(id);

        if (plant != null) {
            this.entityManager.remove(plant);
        }
    }

    @Override
    public void editPlant(Plant plant) throws Exception {
        this.entityManager.merge(plant);
    }

    @Override
    public Plant searchPlantById(int id) throws Exception {
        Plant plant = this.entityManager.find(Plant.class, id);
        return plant;
    }

}
