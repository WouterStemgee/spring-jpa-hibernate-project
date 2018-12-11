/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.zoo.entity;

import javax.persistence.Embeddable;

/**
 *
 * @author Wouter
 */
@Embeddable
public class Address {

    private String street;
    private int number;
    private int zipcode;
    private String city;
    private String country;

    public Address() {
    }

    public Address(String street, int number, int zipcode, String city, String country) {
        this.street = street;
        this.number = number;
        this.zipcode = zipcode;
        this.city = city;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address: " + street + " " + ", " + zipcode + " " + city + ", " + country;
    }

}
