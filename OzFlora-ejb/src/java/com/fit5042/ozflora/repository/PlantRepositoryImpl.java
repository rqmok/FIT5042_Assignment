/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.repository;

import com.fit5042.ozflora.repository.entities.Plant;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * JPA Implementation of the PlantRepository interface.
 *
 * @author Zeeshan
 */
@Stateless
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
        plant.setId(plants.get(plants.size() - 1).getId() + 1);
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

    @Override
    public List<Plant> searchPlants(String name, String description, String family) throws Exception {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(Plant.class);
        Root<Plant> root = query.from(Plant.class);
        
        // Construct a list of predicates for the fields.
        List<Predicate> predicates = new ArrayList<>();
        
        if (!name.isEmpty() && name.length() > 0) {
            Expression<String> expression = root.get("name").as(String.class);
            predicates.add(builder.like(builder.lower(expression), "%" + name.toLowerCase() + "%"));
        }
        
        if (!description.isEmpty() && description.length() > 0) {
            Expression<String> expression = root.get("description").as(String.class);
            predicates.add(builder.like(builder.lower(expression), "%" + description.toLowerCase() + "%"));
        }
        
        if (!family.isEmpty() && family.length() > 0) {
            Expression<String> expression = root.get("family").as(String.class);
            predicates.add(builder.equal(builder.lower(expression), family.toLowerCase()));
        }
        
        // Construct the final query.
        query.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
        
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void savePlant(Plant plant) throws Exception {
        if (plant != null) {
            entityManager.merge(plant);
        }
    }

}
