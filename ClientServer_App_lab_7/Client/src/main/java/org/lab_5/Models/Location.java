package org.lab_5.Models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {
    private Long x; //Поле не может быть null
    private Float y; //Поле не может быть null
    private float z;

    @JsonCreator
    public Location(
            @JsonProperty("x") Long x,
            @JsonProperty("y") Float y,
            @JsonProperty("z") float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }



    public Long getX(){
        return x;
    }
    public Float getY(){
        return y;
    }
    public float getZ(){
        return z;
    }

}
