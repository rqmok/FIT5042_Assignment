/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.mbeans;

import com.fit5042.ozflora.auth.entities.User;
import com.fit5042.ozflora.auth.entities.UserGroup;
import com.fit5042.ozflora.controllers.LoginController;
import com.fit5042.ozflora.repository.UserRepository;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author zeeshan
 */
@ManagedBean(name = "userManagedBean")
@SessionScoped
public class UserManagedBean implements Serializable {
    
    private static final Logger logger = Logger.getLogger(LoginController.class.getName());
    
    @EJB
    private UserRepository userRepository;

    /**
     * Creates a new instance of UserManagedBean
     */
    public UserManagedBean() {
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public List<User> getAllUsers() {
        try {
            return this.userRepository.getAllUsers();
        } catch (Exception e) {
            logger.log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    public User getUser(String email) {
        try {
            return this.userRepository.findUserById(email);
        } catch (Exception e) {
            logger.log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    public UserGroup getUserGroup(String email) {
        try {
            return this.userRepository.findUserGroupById(email);
        } catch (Exception e) {
            logger.log(Level.SEVERE, null, e);
        }
        return null;
    }
    
}
