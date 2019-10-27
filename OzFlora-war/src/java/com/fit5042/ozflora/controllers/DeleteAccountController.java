/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.controllers;

import com.fit5042.ozflora.auth.entities.User;
import com.fit5042.ozflora.mbeans.UserManagedBean;
import java.io.Serializable;
import javax.el.ELContext;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Zeeshan Khan
 */
@Named(value = "deleteAccountController")
@ViewScoped
public class DeleteAccountController implements Serializable {
    
    @ManagedProperty(value = "#{ userManagedBean }")
    private final UserManagedBean userManagedBean;
    
    @ManagedProperty(value = "#{ loginController }")
    private LoginController loginController;
    
    private User user;
    
    private String returnPage;

    /**
     * Creates a new instance of DeleteAccountController
     */
    public DeleteAccountController() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        this.userManagedBean = (UserManagedBean) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "userManagedBean");
        this.loginController = (LoginController) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "loginController");

        String userId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userId");
        this.user = this.userManagedBean.getUser(userId);
        
        this.returnPage = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("returnPage");
    }
    
    public String deleteAccount() {
        this.userManagedBean.removeUser(this.user);
        
        if (this.returnPage.equalsIgnoreCase(PageUrl.PROFILE)) {
            return this.loginController.logout();
        }
        return PageUrl.getPageRedirect(this.returnPage);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserManagedBean getUserManagedBean() {
        return userManagedBean;
    }

    public String getReturnPage() {
        return returnPage;
    }

    public void setReturnPage(String returnPage) {
        this.returnPage = returnPage;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
    
}
