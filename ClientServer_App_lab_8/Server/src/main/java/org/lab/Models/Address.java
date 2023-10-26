package org.lab.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {
    private String street; //Длина строки не должна быть больше 34, Поле не может быть null
    private Location town; //Поле не может быть null

public Address(
        @JsonProperty("street") String street,
        @JsonProperty("town") Location town){
    this.street = street;
    this.town = town;
}
    public String getStreet(){
        return street;
    }
    public Location getTown(){
        return town;
    }
}
