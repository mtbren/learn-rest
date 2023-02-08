package com.projects.rest.webservices.learnrest.entity;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Static filtering at the class level
//@JsonIgnoreProperties({"zip","state"})
// Dynamic filtering
@JsonFilter("AddressDynamicFilter")
public class Address {
    // Static filtering
    @JsonIgnore
    private int apartmentNumber;
    private String street;
    private String city;
    private String state;
    private String zip;

    public Address(int apartmentNumber, String street, String city, String state, String zip) {
        this.apartmentNumber = apartmentNumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    @Override
    public String toString() {
        return "Address{" +
                "apartmentNumber=" + apartmentNumber +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}
