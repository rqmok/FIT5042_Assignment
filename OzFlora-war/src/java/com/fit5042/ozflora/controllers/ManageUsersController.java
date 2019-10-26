/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.controllers;

import com.fit5042.ozflora.auth.entities.User;
import com.fit5042.ozflora.auth.entities.UserGroup;
import com.fit5042.ozflora.auth.entities.WorkerUser;
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
    
    // Searching fields.
    private String name;
    private String email;
    private String groupname;

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
    
    public void searchUsers() {
        this.users = this.userManagedBean.getUsers(name, email, groupname);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    public void removeUser(User user) {
        this.userManagedBean.removeUser(user);
        this.setUsers(this.userManagedBean.getAllUsers());
    }
    
    public String editUser(User user) {
        if (user instanceof WorkerUser) {
            return PageUrl.getPageRedirect(PageUrl.EDIT_WORKER);
        }
        return PageUrl.getPageRedirect(PageUrl.EDIT_WEBSITE_USER);
    }

    public UserManagedBean getUserManagedBean() {
        return userManagedBean;
    }

    public void setUserManagedBean(UserManagedBean userManagedBean) {
        this.userManagedBean = userManagedBean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }
    
}
