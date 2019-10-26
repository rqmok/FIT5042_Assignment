/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.auth.entities;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Zeeshan Khan
 */
@Embeddable
@Access(AccessType.PROPERTY)
public class Address implements Serializable {
    
    @Column(name = "street_number")
    @NotNull
    private String streetNumber;
    
    @Column(name = "street_address")
    @NotNull
    private String streetAddress;
    
    @NotNull
    private String suburb;
    
    @Column(name = "postcode")
    @NotNull
    private int postcode;
    
    @NotNull
    private String state;

    public Address() {
    }

    public Address(String streetNumber, String streetAddress, String suburb, int postcode, String state) {
        this.streetNumber = streetNumber;
        this.streetAddress = streetAddress;
        this.suburb = suburb;
        this.postcode = postcode;
        this.state = state;
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

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
