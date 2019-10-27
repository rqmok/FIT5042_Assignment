/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.services;

import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author zeeshan
 */
@Path("about")
public class AboutService {

    @Context
    private UriInfo context;

    @EJB
    private AboutStorage aboutStorage;

    /**
     * Creates a new instance of AboutService
     */
    public AboutService() {
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAboutText() {
        return aboutStorage.getAboutText();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void setAboutText(@FormParam("aboutText") String aboutText) {
        aboutStorage.setAboutText(aboutText);
    }
}
