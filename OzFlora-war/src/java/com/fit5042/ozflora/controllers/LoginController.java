/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.controllers;

import com.fit5042.ozflora.auth.entities.User;
import com.fit5042.ozflora.auth.entities.UserGroup;
import com.fit5042.ozflora.repository.UserRepository;
import java.io.Serializable;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Zeeshan
 */
@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private static final Logger logger = Logger.getLogger(LoginController.class.getName());

    @EJB
    private UserRepository userRepository;

    private String email;
    private String password;

    private User user;
    private UserGroup userGroup;

    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.login(email, password);
        } catch (ServletException e) {
            logger.log(Level.SEVERE, e.getMessage());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed", null));
            return PageUrl.LOGIN;
        }

        Principal principal = request.getUserPrincipal();

        try {
            this.setUser(this.userRepository.findUserById(principal.getName()));
            this.setUserGroup(this.getUser().getUserGroup());

            logger.log(Level.INFO, "Authenticated user {0}", user.getEmail());

            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.getSessionMap().put("user", user);
            externalContext.getSessionMap().put("user_group", userGroup);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }

        return PageUrl.getPageRedirect(PageUrl.INDEX);
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.logout();
            ((HttpSession) context.getExternalContext().getSession(false)).invalidate();
            this.user = null;
            this.userGroup = null;
        } catch (ServletException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }

        return PageUrl.getPageRedirect(PageUrl.LOGIN);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

}
