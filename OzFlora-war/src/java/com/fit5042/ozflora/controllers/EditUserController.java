/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.controllers;

import com.fit5042.ozflora.auth.AuthenticationUtils;
import com.fit5042.ozflora.auth.entities.User;
import com.fit5042.ozflora.auth.entities.UserGroup;
import com.fit5042.ozflora.mbeans.UserManagedBean;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ELContext;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.view.ViewScoped;

/**
 *
 * @author zeeshan
 */
@Named(value = "editUserController")
@ViewScoped
public class EditUserController implements Serializable {

    private static final Logger logger = Logger.getLogger(EditUserController.class.getName());

    @ManagedProperty(value = "#{ userManagedBean }")
    private final UserManagedBean userManagedBean;
    
    @ManagedProperty(value = "#{ loginController }")
    private LoginController loginController;

    private User user;

    private String password;
    private String confirmPassword;
    
    private String returnPage;

    /**
     * Creates a new instance of EditUserController
     */
    public EditUserController() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        this.userManagedBean = (UserManagedBean) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "userManagedBean");
        this.loginController = (LoginController) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "loginController");

        String userId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userId");
        this.user = this.userManagedBean.getUser(userId);
        this.returnPage = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("returnPage");
    }

    private void addFacesErrorMessage(FacesContext context, String clientId, String errorMessage) {
        FacesMessage message = new FacesMessage(errorMessage);
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        context.addMessage(clientId, message);
        context.renderResponse();
    }

    public void validateForm(ComponentSystemEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();

        UIComponent components = event.getComponent();

        // Get the password.
        UIInput passwordInput = (UIInput) components.findComponent("password");
        String password = passwordInput.getLocalValue() == null ? "" : passwordInput.getLocalValue().toString();
        String passwordId = passwordInput.getClientId();

        // Get the confirm password.
        UIInput confirmPasswordInput = (UIInput) components.findComponent("confirm_password");
        String confirmPassword = confirmPasswordInput.getLocalValue() == null ? "" : confirmPasswordInput.getLocalValue().toString();
        String confirmPasswordId = confirmPasswordInput.getClientId();
        
        if (!password.isEmpty() && confirmPassword.isEmpty()) {
            this.addFacesErrorMessage(context, confirmPasswordId, "Please confirm your password");
        } else if (password.isEmpty() && !confirmPassword.isEmpty()) {
            this.addFacesErrorMessage(context, passwordId, "Please enter a password");
        }

        // Return if unable to retrieve passwords.
        if (!password.isEmpty() && !confirmPassword.isEmpty()) {
            if (!confirmPassword.equals(password)) {
                this.addFacesErrorMessage(context, passwordId, "Password and Confirm Password do not match");
            }
        }
    }
    
    private String getRedirectString() {
        if (returnPage.equalsIgnoreCase(PageUrl.PROFILE)) {
            return this.loginController.logout();
        }
        return PageUrl.getPageRedirect(returnPage);
    }

    public String saveUser() {
        if (user != null) {
            if (password != null && !password.isEmpty() && password.length() > 0) {
                try {
                    this.user.setPassword(AuthenticationUtils.encodeSHA256(password));
                } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
                    logger.log(Level.SEVERE, null, e);
                }
            }

            this.userManagedBean.saveUser(user);
        }
        return this.getRedirectString();
    }

    public String cancel() {
        return PageUrl.getPageRedirect(returnPage);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public String getReturnPage() {
        return returnPage;
    }

    public void setReturnPage(String returnPage) {
        this.returnPage = returnPage;
    }

}
