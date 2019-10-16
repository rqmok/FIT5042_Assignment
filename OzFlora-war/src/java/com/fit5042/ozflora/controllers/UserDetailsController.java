/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.controllers;

import com.fit5042.ozflora.auth.entities.User;
import com.fit5042.ozflora.auth.entities.UserGroup;
import com.fit5042.ozflora.mbeans.UserManagedBean;
import java.util.logging.Logger;
import javax.el.ELContext;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

/**
 *
 * @author zeeshan
 */
@Named(value = "userDetailsController")
@RequestScoped
public class UserDetailsController {
    
    private static final Logger logger = Logger.getLogger(UserDetailsController.class.getName());

    @ManagedProperty(value = "#{ userManagedBean }")
    private final UserManagedBean userManagedBean;
    
    private User user;
    private UserGroup userGroup;

    /**
     * Creates a new instance of UserDetailsController
     */
    public UserDetailsController() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        this.userManagedBean = (UserManagedBean) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "userManagedBean");

        String userId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userId");
        this.user = this.userManagedBean.getUser(userId);
        this.userGroup = this.userManagedBean.getUserGroup(userId);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }
    
}
