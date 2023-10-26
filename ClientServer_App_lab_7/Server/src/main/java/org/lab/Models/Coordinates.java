package org.lab.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Coordinates {
    private int x; //Значение поля должно быть больше -612
    private Long y; //Максимальное значение поля: 420, Поле не может быть null

public Coordinates(
        @JsonProperty("x") int x,
        @JsonProperty("y") Long y) {
    this.x = x;
    this.y = y;
}
    public int getX(){
        return x;
    }
    public Long getY(){
        return y;
    }
}