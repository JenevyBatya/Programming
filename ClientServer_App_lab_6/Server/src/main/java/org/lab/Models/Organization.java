package org.lab.Models;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Organization {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Double annualTurnover; //Поле не может быть null, Значение поля должно быть больше 0
    private String fullName; //Поле может быть null
    private Integer employeesCount; //Поле может быть null, Значение поля должно быть больше 0
    private OrganizationType type; //Поле может быть null
    private Address postalAddress; //Поле не может быть null

    public Organization(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Integer getEmployeesCount() {
        return employeesCount;
    }

    public String getFullName(){
        return fullName;
    }

    public Double getAnnualTurnover(){
        return annualTurnover;
    }


    public OrganizationType getType(){
        return type;
    }

    public void setAnnualTurnover(Double annualTurnover){
        this.annualTurnover=annualTurnover;
    }
    public void setEmployeesCount(Integer employeesCount){
        this.employeesCount=employeesCount;
    }
    public Coordinates getCoordinates(){
        return coordinates;
    }
    public Address getPostalAddress(){
        return postalAddress;
    }
    @JsonCreator
    public Organization(
            @JsonProperty("id") int id,
            @JsonProperty("name") String name,
            @JsonProperty("coordinates") Coordinates coordinates,
            @JsonProperty("creationDate") java.time.LocalDate creationDate,
            @JsonProperty("annualTurnover") Double annualTurnover,
            @JsonProperty("fullName") String fullName,
            @JsonProperty("employeeCount") Integer employeesCount,
            @JsonProperty("type") OrganizationType type,
            @JsonProperty("postalAddress") Address postalAddress) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.annualTurnover = annualTurnover;
        this.fullName = fullName;
        this.employeesCount = employeesCount;
        this.type = type;
        this.postalAddress = postalAddress;
    }

}