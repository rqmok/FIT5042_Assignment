/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.controllers;

import com.fit5042.ozflora.auth.entities.User;
import com.fit5042.ozflora.auth.entities.WebsiteUser;
import com.fit5042.ozflora.repository.UserRepository;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

/**
 *
 * @author Zeeshan
 */
@ManagedBean(name = "registerController")
@SessionScoped
public class RegisterController implements Serializable {
    
    private static final Logger logger = Logger.getLogger(RegisterController.class.getName());
    
    @EJB
    private UserRepository userRepository;
    
    private String name;
    private String email;
    private String password;
    private String confirmPassword;

    /**
     * Creates a new instance of RegisterController
     */
    public RegisterController() {
        this.name = this.email = this.password = this.confirmPassword = "";
    }
    
    public String register() {
        User user = new WebsiteUser(email, name, password);
        
        try {
            this.userRepository.createUser(user);
            logger.info("New user created with email " + email);
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        
        return "registered";
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
            if (this.userRepository.findUserById(email) != null) {
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
    
}
