/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.controllers;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author zeeshan
 */
@Named(value = "aboutController")
@RequestScoped
public class AboutController {

    private String aboutText;

    /**
     * Creates a new instance of AboutStorageManagedBean
     */
    public AboutController() {
        AboutServiceClient client = new AboutServiceClient();
        this.aboutText = client.getAboutText();
    }

    public String getAboutText() {
        return this.aboutText;
    }

    public void setAboutText(String aboutText) {
        this.aboutText = aboutText;
    }

    public void setServiceAboutText() {
        AboutServiceClient client = new AboutServiceClient();
        client.setClientAboutText(this.aboutText);
        this.aboutText = client.getAboutText();
    }

    static class AboutServiceClient {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "http://localhost:8080/OzFlora-war/webresources";

        public AboutServiceClient() {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("about");
        }

        public String getAboutText() throws ClientErrorException {
            WebTarget resource = webTarget;
            return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
        }

        public void setAboutText() throws ClientErrorException {
            webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED).post(null);
        }

        public void setClientAboutText(String aboutText) throws ClientErrorException {
            Form form = new Form();
            form.param("aboutText", aboutText);

            webTarget.request(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
                    .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
        }

        public void close() {
            client.close();
        }
    }

}
