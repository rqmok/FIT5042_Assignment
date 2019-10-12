/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.controllers;

import com.fit5042.ozflora.auth.entities.User;
import com.fit5042.ozflora.auth.entities.UserGroup;
import com.fit5042.ozflora.mbeans.UserManagedBean;
import java.util.List;
import javax.el.ELContext;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

/**
 *
 * @author zeeshan
 */
@Named(value = "manageUsersController")
@RequestScoped
public class ManageUsersController {
    
    @ManagedProperty(value = "#{ userManagedBean }")
    private UserManagedBean userManagedBean;
    
    private List<User> users;

    /**
     * Creates a new instance of ManageUsersController
     */
    public ManageUsersController() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        this.userManagedBean = (UserManagedBean) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "userManagedBean");
        
        this.users = userManagedBean.getAllUsers();
    }
    
    public UserGroup getUserGroup(User user) {
        return this.userManagedBean.getUserGroup(user.getEmail());
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
}
