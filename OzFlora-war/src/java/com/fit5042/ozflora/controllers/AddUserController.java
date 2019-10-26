/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.controllers;

import com.fit5042.ozflora.auth.entities.Address;
import com.fit5042.ozflora.auth.entities.User;
import com.fit5042.ozflora.auth.entities.UserGroup;
import com.fit5042.ozflora.auth.entities.WebsiteUser;
import com.fit5042.ozflora.auth.entities.WorkerUser;
import com.fit5042.ozflora.mbeans.UserManagedBean;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ELContext;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

/**
 *
 * @author zeeshan
 */
@Named(value = "addUserController")
@RequestScoped
public class AddUserController {

    private static final Logger logger = Logger.getLogger(AddUserController.class.getName());

    @ManagedProperty(value = "#{ userManagedBean }")
    private final UserManagedBean userManagedBean;

    @ManagedProperty(value = "#{ loginController }")
    private LoginController loginController;

    private String name;
    private String email;
    private Date dob;
    private String mobile;
    private String streetNumber;
    private String streetAddress;
    private String suburb;
    private String state;
    private int postcode;
    private String password;
    private String confirmPassword;

    /**
     * Creates a new instance of addUserController
     */
    public AddUserController() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        this.userManagedBean = (UserManagedBean) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "userManagedBean");
        this.loginController = (LoginController) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "loginController");
    }

    public void validateForm(ComponentSystemEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();

        UIComponent components = event.getComponent();

        // Get the email.
        UIInput emailInput = (UIInput) components.findComponent("email");
        String email = emailInput.getLocalValue() == null ? "" : emailInput.getLocalValue().toString();
        String emailId = emailInput.getClientId();

        // Get the password.
        UIInput passwordInput = (UIInput) components.findComponent("password");
        String password = passwordInput.getLocalValue() == null ? "" : passwordInput.getLocalValue().toString();
        String passwordId = passwordInput.getClientId();

        // Get the confirm password.
        UIInput confirmPasswordInput = (UIInput) components.findComponent("confirm_password");
        String confirmPassword = confirmPasswordInput.getLocalValue() == null ? "" : confirmPasswordInput.getLocalValue().toString();

        // Return if unable to retrieve passwords.
        if (password.isEmpty() || confirmPassword.isEmpty()) {
            return;
        }

        if (!confirmPassword.equals(password)) {
            // Confirm password and password do not match.
            // Construct and display an error message.
            FacesMessage message = new FacesMessage("Password and Confirm Password do not match");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(passwordId, message);
            context.renderResponse();
        }

        try {
            if (this.userManagedBean.getUser(email) != null) {
                // Email is already being used by another user.
                // Construct and display an error message.
                FacesMessage message = new FacesMessage("Email already exists");
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                context.addMessage(emailId, message);
                context.renderResponse();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    private String getRegisteredString(boolean registered) {
        if (this.loginController.getUserGroup() != null) {
            if (this.loginController.getUserGroup().getGroupName().equals(UserGroup.ADMIN_GROUP)) {
                // Admin has added the user so return them to the manage users page.
                return PageUrl.getPageRedirect(PageUrl.MANAGE_USERS);
            }
        }

        if (registered) {
            return PageUrl.getPageRedirect(PageUrl.REGISTERED);
        }

        return PageUrl.getPageRedirect(PageUrl.LOGIN);
    }

    public String registerWebsiteUser() {
        User user = new WebsiteUser(email, name, password);
        this.userManagedBean.createUser(user);
        return this.getRegisteredString(true);
    }

    public String registerWorkerUser() {
        Address address = new Address(streetNumber, streetAddress, suburb, postcode, state);
        WorkerUser user = new WorkerUser(mobile, dob, address, email, name, password);
        this.userManagedBean.createUser(user);
        return this.getRegisteredString(true);
    }

    public String cancel() {
        return this.getRegisteredString(false);
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

}
