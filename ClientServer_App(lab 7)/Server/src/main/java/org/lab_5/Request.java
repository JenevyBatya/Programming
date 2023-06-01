package org.lab_5;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Request {
    private String command;
    private String arg;
    private String data;


    @JsonCreator
    public Request(@JsonProperty("command") String command,@JsonProperty("arg") String arg,@JsonProperty("data") String data){
        this.command=command;
        this.data=data;
        this.arg=arg;
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
}
