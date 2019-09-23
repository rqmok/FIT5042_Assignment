/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.controllers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Zeeshan
 */
@Named(value = "titleController")
@RequestScoped
public class TitleController {
    
    private String title;

    /**
     * Creates a new instance of TitleController
     */
    public TitleController() {
        this.title = "OzFlora";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
}
