/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.services;

import javax.ejb.Singleton;

/**
 *
 * @author Zeeshan Khan
 */
@Singleton
public class AboutStorageImpl implements AboutStorage {

    private String aboutText = "OzFlora is a directory for native Australian plants. "
            + "The purpose of this directory is to provide users a passion for studying plants with the information that they need."
            + "Do you have any suggestions? Please send an email to suggestions@example.com to clearly outline what you would like to suggest.";

    public String getAboutText() {
        return aboutText;
    }

    public void setAboutText(String aboutText) {
        this.aboutText = aboutText;
    }

}
