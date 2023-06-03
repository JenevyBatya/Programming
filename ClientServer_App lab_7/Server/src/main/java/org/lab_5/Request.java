package org.lab_5;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"command", "arg", "data"})
public class Request {
    private String command;
    private String arg;
    private String data;
    private String username;
    private String password;
    private boolean auth;
    private Integer userId;

    @JsonCreator
    @JsonIgnoreProperties(ignoreUnknown = true)
    public Request(@JsonProperty("command") String command, @JsonProperty("arg") String arg, @JsonProperty("data") String data,@JsonProperty("auth") boolean auth, @JsonProperty("username") String username, @JsonProperty("password") String password) {
        this.command = command;
        this.arg = arg;
        this.data = data;
        this.username = username;
        this.password = password;
        this.auth = auth;
    }


    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Request(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public String getArg() {
        return arg;
    }

    public String getData() {
        return data;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

