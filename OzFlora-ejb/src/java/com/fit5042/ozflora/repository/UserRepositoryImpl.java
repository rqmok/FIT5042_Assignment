/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.repository;

import com.fit5042.ozflora.auth.AuthenticationUtils;
import com.fit5042.ozflora.auth.entities.AdminUser;
import com.fit5042.ozflora.auth.entities.User;
import com.fit5042.ozflora.auth.entities.UserGroup;
import com.fit5042.ozflora.auth.entities.WebsiteUser;
import com.fit5042.ozflora.auth.entities.WorkerUser;
import com.fit5042.ozflora.repository.entities.Plant;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Zeeshan
 */
@Stateless
@LocalBean
public class UserRepositoryImpl implements UserRepository {
    
    @PersistenceContext(unitName = "OzFlora-ejbPU")
    private EntityManager entityManager;
    
    @Override
    public User createUser(User user) throws Exception {
        try {
            user.setPassword(AuthenticationUtils.encodeSHA256(user.getPassword()));
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
        }
        
        UserGroup userGroup = new UserGroup();
        userGroup.setEmail(user.getEmail());
        
        if (user instanceof WorkerUser) {
            userGroup.setGroupName(UserGroup.WORKERS_GROUP);
        } else if (user instanceof AdminUser) {
            userGroup.setGroupName(UserGroup.ADMIN_GROUP);
        } else {
            userGroup.setGroupName(UserGroup.USERS_GROUP);
        }
        
        user.setUserGroup(userGroup);
        userGroup.setUser(user);
        
        entityManager.persist(user);
        
        return user;
    }

    @Override
    public void removeUser(User user) throws Exception {
        if (!entityManager.contains(user)) {
            user = entityManager.merge(user);
        }
        entityManager.remove(user);
    }

    @Override
    public void saveUser(User user) throws Exception {
        entityManager.merge(user);
    }
    
    @Override
    public User findUserById(String id) throws Exception {
        return entityManager.find(User.class, id);
    }

    @Override
    public UserGroup findUserGroupById(String id) throws Exception {
        return entityManager.find(UserGroup.class, id);
    }
    
    @Override
    public void savePlantToUser(User user, Plant plant) throws Exception {
        if (user instanceof WebsiteUser) {
            ((WebsiteUser) user).addPlant(plant);
            entityManager.merge(user);
        } else {
            throw new IllegalArgumentException("Plants can only be saved by web site users.");
        }
    }
    
    @Override
    public void removePlantFromUser(User user, Plant plant) throws Exception {
        if (user instanceof WebsiteUser) {
            ((WebsiteUser) user).removePlant(plant);
            entityManager.merge(user);
        } else {
            throw new IllegalArgumentException("Plants can only be removed by web site users.");
        }
    }

    @Override
    public List<User> getAllUsers() throws Exception {
        return this.entityManager.createNamedQuery(User.GET_ALL_QUERY_NAME).getResultList();
    }

    @Override
    public List<User> searchUsers(String name, String email, String groupname) throws Exception {
        Query query = entityManager.createQuery(
                "SELECT u FROM User u "
                        + "JOIN u.userGroup g "
                        + "WHERE lower(u.name) LIKE :namePattern AND "
                        + "lower(u.email) LIKE :emailPattern AND "
                        + "lower(g.groupName) LIKE :groupPattern"
        );
        query.setParameter("namePattern", "%" + name.toLowerCase() + "%");
        query.setParameter("emailPattern", "%" + email.toLowerCase() + "%");
        query.setParameter("groupPattern", "%" + groupname.toLowerCase() + "%");
        
        return query.getResultList();
    }
    
}
