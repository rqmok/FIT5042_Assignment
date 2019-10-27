/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.controllers;

/**
 * Container for JSF pages that are used by controllers.
 * 
 * @author Zeeshan Khan
 */
public final class PageUrl {
    public static String getPageRedirect(String page) {
        return page + "?faces-redirect=true";
    }
    
    public static String INDEX = "index";
    
    public static String LOGIN = "login";
    
    public static String REGISTERED = "registered";
    
    public static String MANAGE_USERS = "manageusers";
    
    public static String PLANT_DETAILS = "detail";
    
    public static String EDIT_WORKER = "editworker";
    
    public static String EDIT_WEBSITE_USER = "editwebsiteuser";
    
    public static String PLANTS = "plants";
    
    public static String PROFILE = "profile";
}
